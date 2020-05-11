package org.gudartem.aars.configuration;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.gudartem.aars.api.constants.MDCConstants;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;

@Aspect
@Component
public class AarsAspect {

    @Around("@within(org.springframework.web.bind.annotation.RestController) && execution(public * *(..))")
    public Object doExecuteRestControllerMethods(ProceedingJoinPoint pjp) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .currentRequestAttributes())
                .getRequest();

        MDC.put(MDCConstants.ENDPOINT_PATH, MessageFormat.format("{0} {1}", request.getMethod(), request.getRequestURI()));

        Object value;

        try {
            value = pjp.proceed();
        } catch (Throwable throwable) {
            throw throwable;
        }

        MDC.clear();
        return value;
    }

    @Around("@within(org.springframework.web.bind.annotation.ControllerAdvice) && execution(public * *(..))")
    public Object doExecuteControllerAdviceMethods(ProceedingJoinPoint pjp) throws Throwable {
        Object value;

        try {
            value = pjp.proceed();
        } catch (Throwable throwable) {
            throw throwable;
        } finally {
            MDC.clear();
        }

        return value;
    }
}
