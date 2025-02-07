package com.example.JobManagementScv.config.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class ServiceLoggingAspect {

    private static final Logger log = LoggerFactory.getLogger(ServiceLoggingAspect.class);

    @Before("execution(* com.example.JobManagementScv.service.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        log.info("Entering method: {} in class: {}", joinPoint.getSignature().getName(), joinPoint.getTarget().getClass().getSimpleName());
        Object[] args = joinPoint.getArgs();
        if (args != null && args.length > 0) {
            log.debug("Arguments: {}", Arrays.toString(args));
        }
    }

    @AfterReturning(pointcut = "execution(* com.example.JobManagementScv.service.*.*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        log.info("Exiting method: {} in class: {}", joinPoint.getSignature().getName(), joinPoint.getTarget().getClass().getSimpleName());
        if (result != null) {
            log.debug("Return value: {}", result);
        }
    }

    @AfterThrowing(pointcut = "execution(* com.example.JobManagementScv.service.*.*(..))", throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable ex) {
        log.error("Exception in method: {} in class: {}", joinPoint.getSignature().getName(),
                joinPoint.getTarget().getClass().getSimpleName(), ex);
    }
}
