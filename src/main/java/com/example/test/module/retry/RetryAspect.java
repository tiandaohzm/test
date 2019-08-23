package com.example.test.module.retry;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class RetryAspect {

    @Around("@annotation(retry)")
    public Object execute(ProceedingJoinPoint joinPoint, Retry retry) throws Exception {
        new RetryTemplate() {
            @Override
            protected void doBiz() throws Exception{
                try {
                    joinPoint.proceed();
                } catch (Throwable e) {
                    if(e instanceof Exception){
                        throw (Exception)e;
                    }
                }
            }
        }.setRetryTime(retry.count()).execute();
        return null;
    }


}
