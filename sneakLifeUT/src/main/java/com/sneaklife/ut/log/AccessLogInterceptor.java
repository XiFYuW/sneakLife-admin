package com.sneaklife.ut.log;

import com.sneaklife.ut.date.DateUtil;
import com.sneaklife.ut.iws.IwsContext;
import com.sneaklife.ut.servlet.SneakLifeServlet;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/12/8 11:52
 */
@Component
@Aspect
@Order(1)
public class AccessLogInterceptor {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public AccessLogInterceptor(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Around("@annotation(com.sneaklife.ut.log.AccessLogAn) && @annotation(accessLogAn)")
    public Object sneakLifeLogAround(ProceedingJoinPoint point, AccessLogAn accessLogAn) throws Throwable{
        SneakLifeServlet sneakLifeServlet = IwsContext.getSneakLifeServletObject();
        AccessLog log = new AccessLog();
        log.setCreateDate(new Date());
        String ip = sneakLifeServlet.getIp();
        log.setIp(ip);
        log.setBrowser(sneakLifeServlet.getBrowser());
        log.setAddress(sneakLifeServlet.getCityInfo(ip));
        log.setAccessInterface(accessLogAn.value());
        Object[] objects = point.getArgs();
        Object object;
        long startTime = DateUtil.getMilli();
        long endTime;
        try {
            object = point.proceed(objects);
            endTime = DateUtil.getMilli();
        } catch (Throwable throwable) {
            endTime = DateUtil.getMilli();
            log.setLogEx(throwable.toString());
            log.setAccessTime(endTime - startTime);
            mongoTemplate.save(log);
            throw throwable;
        }
        log.setAccessTime(endTime - startTime);
        mongoTemplate.save(log);
        return object;
    }
}
