package com.harukaze.api.aop;

import cn.hutool.json.JSONUtil;
import com.harukaze.api.controller.admin.AdminGoodsController;
import com.harukaze.api.entity.History;
import com.harukaze.api.mapper.HistoryMapper;
import com.harukaze.api.service.HistoryService;
import com.harukaze.api.util.HttpContextUtils;
import com.harukaze.api.util.IpUtils;
import com.harukaze.api.util.UserThreadLocal;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@Component
@Aspect
@Slf4j
public class LogAspect {
    @Autowired
    HistoryService historyService;

    @Autowired
    HistoryMapper historyMapper;

    @Pointcut("@annotation(com.harukaze.api.aop.LogAnnotation)")
    public void pc(){}

    @Around("pc()")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        Long beginTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        Long time = System.currentTimeMillis() - beginTime;
        recordLog(joinPoint, time);
        return result;
    }

    private void recordLog(ProceedingJoinPoint joinPoint, Long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        LogAnnotation logAnnotation = method.getAnnotation(LogAnnotation.class);
        Object[] args = joinPoint.getArgs();
        Long params = (Long) args[0];
        if (UserThreadLocal.get() != null) {
            History history = new History();
            history.setCreateDate(System.currentTimeMillis());
            history.setGoodsId(params);
            history.setUserId(UserThreadLocal.get().getId());
            historyMapper.insert(history);
        }

        //获取request, 设置ip地址
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();


        log.info("=======================log============================");
        log.info("module:{}",logAnnotation.module());
        log.info("operation:{}",logAnnotation.operator());

        //请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        log.info("request method:{}",className+"."+signature.getName()+"()");
        log.info("params:{}",params);
        log.info("ip:{}", IpUtils.getIpAddr(request));
        log.info("excute time : {} ms", time);
        log.info("browser:{}", request.getHeader("User-Agent"));
        log.info("=======================log end============================");
    }

}











