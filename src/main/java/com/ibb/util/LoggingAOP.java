package com.ibb.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAOP {

    /*
    Parmeter won Methoden auslesen?
     */
    @Before("execution(* com.ibb.service.*.*(..))")
    public void logMethodParams(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        System.out.println("[AOP] Methode: " + joinPoint.getSignature().getName());
        for (Object arg : args) {
            System.out.println("[AOP] Parameter: " + arg);
        }
    }

//    /*
//      Rückgabewert verändern
//
//     */
//    @Around("execution(* com.example.service.UserService.getWelcomeMessage(..))")
//    public Object modifyReturnValue(ProceedingJoinPoint joinPoint) throws Throwable {
//        Object result = joinPoint.proceed();
//        System.out.println("[AOP] Originaler Rückgabewert: " + result);
//        return "[AOP] Geänderte Nachricht: " + ((String) result).toUpperCase();
//    }
}
