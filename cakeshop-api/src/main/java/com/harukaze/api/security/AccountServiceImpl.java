package com.harukaze.api.security;

import com.harukaze.api.entity.Role;
import com.harukaze.api.entity.User;
import com.harukaze.api.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * @PackageName: com.harukaze.front.security
 * @ClassName: AccountServiceImpl
 * @Description:
 * @Author: doki
 * @Date: 28/11/2021 12:35 AM
 */
@Slf4j
@Service
public class AccountServiceImpl implements UserDetailsService {
    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }

        return new Account(username, user.getPassword(), getAuthorities(user),user.getStatus());
    }

    public Collection<? extends GrantedAuthority> getAuthorities(User user) {
        Role role = userService.findRole(user.getId());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ROLE_").append(role.getName());


        return AuthorityUtils.commaSeparatedStringToAuthorityList(stringBuilder.toString());
    }
}
