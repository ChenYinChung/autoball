package com.nexio.autoball.component;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 紀錄每個Repository method 處理花費時長
 */
@Aspect
@Component
public class RepositoryMethodInterceptor {

    private static Logger logger = LoggerFactory.getLogger(RepositoryMethodInterceptor.class);

    @Around("execution(* com.nexio.autoball..*.*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {

        long start = System.currentTimeMillis();
        try {
            String className = joinPoint.getSignature().getDeclaringTypeName();
            String methodName = joinPoint.getSignature().getName();
            Object result = joinPoint.proceed();
            long elapsedTime = System.currentTimeMillis() - start;
            logger.info("Method " + className + "." + methodName + " taken time : " + elapsedTime + " ms");

            return result;
        } catch (IllegalArgumentException e) {
            logger.error("Illegal argument " + Arrays.toString(joinPoint.getArgs()) + " in "
                + joinPoint.getSignature().getName() + "()");
            throw e;
        }
    }
}
