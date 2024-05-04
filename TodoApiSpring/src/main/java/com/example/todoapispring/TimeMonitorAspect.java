package com.example.todoapispring;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeMonitorAspect {
    @Around("@annotation(TimeMonitor)")
    public void logTime(ProceedingJoinPoint joinPoint){
        long start = System.currentTimeMillis();
        try{
            //execute the joinpoint
            joinPoint.proceed();
        }catch (Throwable e){
            System.out.println("Something wrong happened during the execution");
        }finally {
            long end = System.currentTimeMillis();
            long totalExecutionTime = end - start;
            System.out.println("Total time of the execution of method is : " + totalExecutionTime + " ...");
        }
    }
}
