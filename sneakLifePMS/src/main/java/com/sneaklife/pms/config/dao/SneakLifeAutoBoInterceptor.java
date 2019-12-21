package com.sneaklife.pms.config.dao;

import com.sneaklife.pms.dao.system.authority.opera.OperaBoMapper;
import com.sneaklife.pms.entity.OperaBo;
import com.sneaklife.ut.iws.IwsContext;
import com.sneaklife.ut.jopo.JoinPointUtil;
import com.sneaklife.ut.string.StringUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/12/20 12:21
 */
@Component
@Aspect
@Order(1)
public class SneakLifeAutoBoInterceptor {

    @Autowired
    private OperaBoMapper operaBoMapper;

    private static final Logger log = LoggerFactory.getLogger(SneakLifeAutoBoInterceptor.class);

    @Around("@annotation(com.sneaklife.pms.config.dao.SneakLifeAutoBo) && @annotation(sneakLifeAutoBo)")
    public Object sneakLifeAutoBoAround(ProceedingJoinPoint point, SneakLifeAutoBo sneakLifeAutoBo) throws Throwable{
        Query query = JoinPointUtil.getAnnotation(point, Query.class);
        Map<String,Object> sneakLifeAutoBoValue = paresMap(sneakLifeAutoBo.value());
        Map<String,Object> map = getMap(point);
        List<OperaBo> operaBoList = operaBoMapper.findOperaBoByShow(map);
        StringBuilder sb = new StringBuilder(query.value());
        if(IwsContext.isNotNull(operaBoList)){
            operaBoList.forEach(operaBo -> {
                String field = operaBo.getField();
                String key = String.valueOf(sneakLifeAutoBoValue.get(field));
                if(!StringUtil.isEmpty(key)){
                    Object value = map.get(field);
                    sb.append(key).append("=").append("'").append(value).append("'");
                }
            });
        }
        String val = sb.toString();
        setQueryValue(query, val);
        log.info("页面条件查询执行sql：{}", val);
        return point.proceed();
    }

    private void setQueryValue(Query query, String val) throws NoSuchFieldException, IllegalAccessException {
        Map<String,Object> map = JoinPointUtil.proxyFieldByAn(query);
        map.put("value", val);
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> getMap(ProceedingJoinPoint point){
        Object[] objects = point.getArgs();
        Map<String, Object> map = new HashMap<>();
        for (Object object : objects) {
            if(object instanceof Map){
                map.putAll((Map<String,Object>) object);
            }
        }
        return map;
    }

    private Map<String, Object> paresMap(String[] sneakLifeAutoBoValue){
        Map<String,Object> map = new HashMap<>();
        for (String v : sneakLifeAutoBoValue){
            String[] s = v.split("=");
            map.put(s[0], s[1]);
        }
        return map;
    }
}
