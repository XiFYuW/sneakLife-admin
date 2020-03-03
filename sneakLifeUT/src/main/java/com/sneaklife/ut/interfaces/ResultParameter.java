package com.sneaklife.ut.interfaces;

import java.util.HashMap;
import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/2/8 16:38
 */
public interface ResultParameter<T> {

    /**
     * 获取map类型数据，与ParameterTransformation.paramTrans方法相似
     * @param t Object对象
     * @return map类型数据
     */
    Map<String,Object> getMap(T t);

    default Map<String,Object> defaultMap(Map<String,Object> o){
        Map<String, Object> map = new HashMap<>();
        map.put("id", o.get("id"));
        map.put("textName", o.get("textName"));
        return map;
    }
}
