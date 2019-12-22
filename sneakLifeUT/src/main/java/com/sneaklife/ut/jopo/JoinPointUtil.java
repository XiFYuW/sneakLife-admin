package com.sneaklife.ut.jopo;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/12/20 14:47
 */
public class JoinPointUtil {

    public static Method getMethod(JoinPoint joinPoint){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        return signature.getMethod();
    }

    public static <T extends Annotation> T getAnnotation(JoinPoint joinPoint, Class<T> annotationClass){
        return getMethod(joinPoint).getAnnotation(annotationClass);
    }

    public static <T extends Annotation> T getDeclaredAnnotations(JoinPoint joinPoint, Class<T> annotationClass){
        return getMethod(joinPoint).getDeclaredAnnotation(annotationClass);
    }

    @SuppressWarnings("unchecked")
    public static Map<String,Object> proxyFieldByAn(Annotation annotation) throws NoSuchFieldException, IllegalAccessException {
        InvocationHandler invocationHandler = Proxy.getInvocationHandler(annotation);
        Field field = invocationHandler.getClass().getDeclaredField("memberValues");
        field.setAccessible(true);
        return (Map<String,Object>) field.get(invocationHandler);
    }

    public static Object[] getArgs(JoinPoint joinPoint){
        return joinPoint.getArgs();
    }
}
