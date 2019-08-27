package com.sneaklife.log;

import com.sneaklife.dao.log.SneakLifeLogJpa;
import com.sneaklife.date.DateUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/8/25 10:01 sneakLifeLogDB
 */
@Aspect
@Component
@SuppressWarnings("unchecked")
public class SneakLifeLogInterceptor {

    @Autowired
    private SneakLifeLogJpa sneakLifeLogJpa;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Around("@annotation(com.sneaklife.log.SneakLifeLog) && @annotation(sneakLifeLog)")
    public Object sneakLifeLogAround(ProceedingJoinPoint point, SneakLifeLog sneakLifeLog) throws Throwable{
        com.sneaklife.dao.entity.SneakLifeLog sneakLifeLogs = new com.sneaklife.dao.entity.SneakLifeLog();
        sneakLifeLogs.setId(Integer.valueOf(String.valueOf(DateUtil.getSecond())));
        sneakLifeLogs.setIsDel(0);
        sneakLifeLogs.setCreateDate(new Date());
        sneakLifeLogs.setUpdateDate(new Date());
        Object[] objects = point.getArgs();
        buildLog(objects, point, sneakLifeLogs);
        Object object;
        try {
            object = point.proceed(objects);
        } catch (Throwable throwable) {
            setLogEx(throwable, sneakLifeLogs);
            mongoTemplate.save(sneakLifeLogs);
            throw throwable;
        }
        setLogOut(object, sneakLifeLogs);
        mongoTemplate.save(sneakLifeLogs);
        return object;
    }

    private void buildLog(Object[] objects, ProceedingJoinPoint point, com.sneaklife.dao.entity.SneakLifeLog sneakLifeLogs){
        String methodName = setLogMe(point, sneakLifeLogs);
        setLogText(methodName, sneakLifeLogs);
        setLogIn(objects, sneakLifeLogs);
    }

    private void setLogEx(Throwable throwable, com.sneaklife.dao.entity.SneakLifeLog sneakLifeLogs){
        sneakLifeLogs.setLogEx("异常信息：【" + throwable.getLocalizedMessage() + "】");
    }

    private void setLogOut(Object object, com.sneaklife.dao.entity.SneakLifeLog sneakLifeLogs){
        sneakLifeLogs.setLogOut("出参数据：【" + object.toString() + "】");
    }

    private void setLogIn(Object[] objects, com.sneaklife.dao.entity.SneakLifeLog sneakLifeLogs){
        StringBuilder sb = new StringBuilder("入参数据：【");
        Map<String,Object> map = new HashMap<>();
        for (int i = 0; i < objects.length; i++) {
            map.put(String.valueOf(i), objects[i].toString());
        }
        sb.append(map.toString()).append("】");
        sneakLifeLogs.setLogIn(sb.toString());
    }

    private void setLogText(String methodName, com.sneaklife.dao.entity.SneakLifeLog sneakLifeLogs){
        String stringBuilder = "执行方法【" + methodName + "】";
        sneakLifeLogs.setLogText(stringBuilder);
    }

    private String setLogMe(ProceedingJoinPoint point, com.sneaklife.dao.entity.SneakLifeLog sneakLifeLogs){
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        Method method = methodSignature.getMethod();
        sneakLifeLogs.setMeModifier(Modifier.toString(method.getModifiers()));
        sneakLifeLogs.setMeReturnType(method.getReturnType().getTypeName());
        sneakLifeLogs.setMeDeclaring(method.getDeclaringClass().getTypeName());
        sneakLifeLogs.setMeName(method.getName());
        sneakLifeLogs.setMeParameterType("(" + separateWithCommas(method.getParameterTypes()) + ")");
        sneakLifeLogs.setMeExceptionType(separateWithCommas(method.getExceptionTypes()));
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
