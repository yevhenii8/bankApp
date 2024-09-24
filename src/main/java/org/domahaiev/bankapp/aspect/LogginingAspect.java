package org.domahaiev.bankapp.aspect;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class LogginingAspect {

    @Pointcut("execution(public * org.domahaiev.bankapp.controller.rest.*.*(..))")
    public void controllerLog() {
    }

    @Pointcut("execution(public * org.domahaiev.bankapp.service.interf.*.*(..))")
    public void serviceLog() {
    }

    @Before("controllerLog()")
    public void doBeforeController(JoinPoint jp) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        log.info("NEW REQUEST:\n" +
                        "IP : {}\n" +
                        "URL : {}\n" +
                        "HTTP_METHOD : {}\n" +
                        "CONTROLLER_METHOD : {}.{}",
                request.getRemoteAddr(),
                request.getRequestURL().toString(),
                request.getMethod(),
                jp.getSignature().getDeclaringTypeName(),
                jp.getSignature().getName());
    }

    @Before("serviceLog()")
    public void logBeforeService(JoinPoint joinPoint) {
        log.info("RUN SERVICE:\n" +
                        "SERVICE_METHOD: {}.{}",
                joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
    }

    @AfterReturning(returning = "returnObject", pointcut = "controllerLog()")
    public void logAfterReturning(Object returnObject) {
        log.info("RETURN RESULT: {}\n" +
                        "END OF REQUEST",
                returnObject);
    }

    @AfterThrowing(throwing = "ex", pointcut = "controllerLog()")
    public void logAfterThrowing(JoinPoint joinPoint, Exception ex) {
        log.error("Request throw an exception. Cause - {}. {}",
                Arrays.toString(joinPoint.getArgs()), ex.getMessage());
    }
}
