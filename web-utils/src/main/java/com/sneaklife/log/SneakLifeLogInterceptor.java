package com.sneaklife.log;

import com.sneaklife.dao.log.SneakLifeLogJpa;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/8/25 10:01
 */
@Aspect
@Order(5)
@Component
@SuppressWarnings("unchecked")
public class SneakLifeLogInterceptor {

    @Autowired
    private SneakLifeLogJpa sneakLifeLogJpa;

    @Around("@annotation(com.sneaklife.log.SneakLifeLog) && @annotation(sneakLifeLog)")
    public Object sneakLifeLogAround(ProceedingJoinPoint point, SneakLifeLog sneakLifeLog) {
        Object[] objects = point.getArgs();
        Object object = null;
        try {
            object = point.proceed(objects);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return object;
    }
}
