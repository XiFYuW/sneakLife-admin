package com.sneaklife.ut.log;

import com.sneaklife.ut.iws.IwsContext;
import com.sneaklife.ut.servlet.SneakLifeServlet;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/12/8 11:52
 */
@Component
@Aspect
@Order(1)
public class LogicalLogInterceptor {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public LogicalLogInterceptor(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Around("@annotation(com.sneaklife.ut.log.LogicalLogAn) && @annotation(logicalLogAn)")
    public Object sneakLifeLogAround(ProceedingJoinPoint point, LogicalLogAn logicalLogAn) throws Throwable{
        SneakLifeServlet sneakLifeServlet = IwsContext.getSneakLifeServletObject();
        String sessionId = "";
        if (null != sneakLifeServlet) {
            sessionId = sneakLifeServlet.getSessionId();
        }
        LogicalLog log = new LogicalLog();
        log.setSessionId(sessionId);
        log.setCreateDate(new Date());
        Object[] objects = point.getArgs();
        buildLog(objects, point, log);
        Object object;
        try {
            object = point.proceed(objects);
        } catch (Throwable throwable) {
            setLogEx(throwable, log);
            mongoTemplate.insert(log);
            throw throwable;
        }
        setLogOut(object, log);
        mongoTemplate.insert(log);
        return object;
    }

    private void buildLog(Object[] objects, ProceedingJoinPoint point, LogicalLog log){
        String methodName = setLogMe(point, log);
        setLogText(methodName, log);
        setLogIn(objects, log);
    }

    private void setLogEx(Throwable throwable,  LogicalLog log){
        log.setLogEx("异常信息：【" + throwable.toString() + "】");
    }

    private void setLogOut(Object object,  LogicalLog log){
        if (IwsContext.isNotNull(object)) {
            log.setLogOut("出参数据：【" + object.toString() + "】");
        } else {
            log.setLogOut("出参数据：【void】");
        }
    }

    private void setLogIn(Object[] objects,  LogicalLog log){
        StringBuilder sb = new StringBuilder("入参数据：【");
        Map<String,Object> map = new HashMap<>();
        for (int i = 0; i < objects.length; i++) {
            map.put(String.valueOf(i), String.valueOf(objects[i]));
        }
        sb.append(map.toString()).append("】");
        log.setLogIn(sb.toString());
    }

    private void setLogText(String methodName, LogicalLog log){
        log.setLogText("执行方法【" + methodName + "】");
    }

    private String setLogMe(ProceedingJoinPoint point, LogicalLog log){
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        Method method = methodSignature.getMethod();
//        log.setMeModifier(Modifier.toString(method.getModifiers()));
//        log.setMeReturnType(method.getReturnType().getTypeName());
//        log.setMeDeclaring(method.getDeclaringClass().getTypeName());
//        log.setMeName(method.getName());
//        log.setMeParameterType("(" + separateWithCommas(method.getParameterTypes()) + ")");
//        log.setMeExceptionType(separateWithCommas(method.getExceptionTypes()));
        return method.toString();
    }

    private String separateWithCommas(Class<?>[] types) {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < types.length; j++) {
            sb.append(types[j].getTypeName());
            if (j < (types.length - 1)){
                sb.append(",");
            }
        }
        return sb.toString();
    }
}
