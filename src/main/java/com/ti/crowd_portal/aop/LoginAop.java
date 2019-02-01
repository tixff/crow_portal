package com.ti.crowd_portal.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

/**
 * @author Ti
 * @date 2019/2/1
 */
@Aspect
@Configuration
public class LoginAop {

    @Pointcut("execution(* com.ti.crowd_portal.controller.*.*(..))")
    public void pointcut(){}

    @Before("pointcut()")
    public void login(){

    }
}
