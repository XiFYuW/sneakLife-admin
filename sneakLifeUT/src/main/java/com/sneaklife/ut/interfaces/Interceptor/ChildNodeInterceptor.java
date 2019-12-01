package com.sneaklife.ut.interfaces.Interceptor;

import com.sneaklife.ut.iws.IwsContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/8/21 15:59
 */
@Aspect
@Component
@SuppressWarnings("unchecked")
public class ChildNodeInterceptor {

    @Around("@annotation(com.sneaklife.ut.interfaces.Interceptor.ChildNode) && @annotation(childNode)")
    public Map<String,Object> childNodeAround(ProceedingJoinPoint point, ChildNode childNode) throws Throwable{
        Object[] args = point.getArgs();
        Map<String,Object> data = (Map<String,Object>) point.proceed(args);
        putUrl(data, childNode);
        return data;
    }

    private void putUrl(Map<String,Object> data, ChildNode childNode){
        List<Map<String,Object>> childMenu = (List<Map<String,Object>>) data.get("nodes");
        if(IwsContext.isNotNull(childMenu)){
            data.put("url", childNode.itemUrl());
            childMenu.forEach(map -> putUrl(map, childNode));
        }
    }
}
