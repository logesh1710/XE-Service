package io.gomobi.quartz.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class AOPConfig {

    @Around("execution(* io.gomobi.quartz.api..*(..))")
    public Object aroundApi(ProceedingJoinPoint jointPoint) throws Throwable {
        long start = System.currentTimeMillis();
        String methodName = jointPoint.getSignature().toShortString();

        try {
            log.info("➡ API Starting: {} & request Data {}", methodName, jointPoint.getArgs());
            Object result = jointPoint.proceed();
            long time = System.currentTimeMillis() - start;
            log.info("✅ API Completed: {} in {}ms", methodName, time);
            return result;
        } catch (Throwable ex) {
            log.error("❌ API Exception in: {} - {}", methodName, ex.getMessage(), ex);
            throw ex;
        }
    }

    @Around("execution(* io.gomobi.quartz.service..*(..))")
    public Object aroundService(ProceedingJoinPoint jointPoint) throws Throwable {
        long start = System.currentTimeMillis();
        String methodName = jointPoint.getSignature().toShortString();

        try {
            log.info("➡ Service Starting: {} & request Data {}", methodName, jointPoint.getArgs());
            Object result = jointPoint.proceed();
            long time = System.currentTimeMillis() - start;
            log.info("✅ Service Completed: {} in {}ms", methodName, time);
            return result;
        } catch (Throwable ex) {
            log.error("❌ Service  Exception in: {} - {}", methodName, ex.getMessage(), ex);
            throw ex;
        }
    }

}
