package com.harukaze.api.config;

import cn.hutool.json.JSONUtil;

import com.harukaze.api.security.JwtAuthenticationFilter;
import com.harukaze.api.util.JwtUtils;
import com.harukaze.api.util.RedisUtil;
import com.harukaze.api.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @PackageName: com.harukaze.front.config
 * @ClassName: SecurityConfig
 * @Description:
 * @Author: doki
 * @Date: 28/11/2021 12:28 AM
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String[] WRIHTE_URL = {
            "/swagger-ui.html",
            "/v2/api-docs", // swagger api json
            "/swagger-resources/configuration/ui", // ???????????????????????????
            "/swagger-resources", // ????????????api-docs???URI
            "/swagger-resources/configuration/security", // ????????????
            "/swagger-resources/**",
            //??????????????????????????????swagger???????????????????????????????????????????????????/webjars???????????????????????????????????????????????????????????????(2020-10-23)
            "/webjars/**"
    };

    @Autowired
    RedisUtil redisUtil;

    @Bean
    JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        return new JwtAuthenticationFilter(authenticationManager());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(WRIHTE_URL).permitAll()
                .antMatchers("/admin/**","/logout").authenticated()
                .and()
                .formLogin()
                .loginProcessingUrl("/login")
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        response.setContentType("application/json;charset=utf-8");
                        response.setHeader("Access-Control-Expose-Headers", JwtUtils.getHeader());
                        String token = JwtUtils.generateToken(authentication.getName());
                        response.setHeader("Authorization", token);
                        redisUtil.set("token_"+token, token, JwtUtils.getExpire());
                        response.getWriter().write(JSONUtil.toJsonStr(Result.succ("",0)));
                    }
                })
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
                        response.setContentType("application/json;charset=utf-8");
                        if (exception instanceof UsernameNotFoundException) {
                            response.getWriter().write(JSONUtil.toJsonStr(Result.fail(exception.getMessage())));
                            return;
                        }
                        response.getWriter().write(JSONUtil.toJsonStr(Result.fail("????????????")));
                    }
                })
                .and()
                .logout()
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        String token = request.getHeader(JwtUtils.getHeader());
                        redisUtil.del("token_"+token);
                        response.setContentType("application/json;charset=utf-8");
                        response.getWriter().write(JSONUtil.toJsonStr(Result.succ("ok",0)));
                    }
                })
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                .accessDeniedHandler(new AccessDeniedHandler() {
                    @Override
                    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
                        httpServletResponse.setContentType("application/json;charset=utf-8");
//                        httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
                        httpServletResponse.getWriter().write(JSONUtil.toJsonStr(Result.fail(403,"????????????",null)));
                    }
                })
                .authenticationEntryPoint(new AuthenticationEntryPoint() {
                    @Override
                    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
                        httpServletResponse.setContentType("application/json;charset=utf-8");
//                        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        httpServletResponse.getWriter().write(JSONUtil.toJsonStr(Result.fail("????????????")));
                    }
                })
                .and()
                .cors()
                .and()
                .csrf().disable();
        http.addFilter(jwtAuthenticationFilter());
    }
}
