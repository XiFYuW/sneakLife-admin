package com.sneaklife.interfaces.Interceptor;

import com.sneaklife.common.CommonUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.HashMap;
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

    @Around("@annotation(com.sneaklife.interfaces.Interceptor.ChildNode) && @annotation(childNode)")
    public Map<String,Object> childNodeAround(ProceedingJoinPoint point, ChildNode childNode) {
        Object[] args = point.getArgs();
        Map<String,Object> data = new HashMap<>();
        try {
            data = (Map<String,Object>) point.proceed(args);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        putUrl(data, childNode);
        return data;
    }

    private void putUrl(Map<String,Object> data, ChildNode childNode){
        List<Map<String,Object>> childMenu = (List<Map<String,Object>>) data.get("nodes");
        if(CommonUtil.isNull(childMenu)){
            data.put("url", childNode.itemUrl());
            childMenu.forEach(map -> {
                putUrl(map, childNode);
            });
        }
    }
}
