package com.springcloud.common.annotation.handler;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: Think
 * @date: 2020-06-27 13:01
 */
@Component
@Aspect
@Slf4j
public class LogAspect {

    ThreadLocal<Long> currentTime = new ThreadLocal<>();

    /**
     * 配置切入点
     */
    @Pointcut("@annotation(com.springcloud.common.annotation)")
    public void logPointcut() {
        // 该方法无方法体,主要为了让同类中其他方法使用此切入点
    }

    /**
     * 配置环绕通知，使用在方法logPointcut()上注册的切入点
     * @param proceedingJoinPoint
     * @return
     */
    @Around("logPointcut()")
    public Object logAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object result;
        currentTime.set(System.currentTimeMillis());
        result = proceedingJoinPoint.proceed();
        //执行时间
        long time = System.currentTimeMillis() - currentTime.get();
        return result;
    }

    /**
     * 配置异常通知
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "logPointcut()",throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        //执行时间
        long time = System.currentTimeMillis() - currentTime.get();
        // 对于异常的处理
    }
}
