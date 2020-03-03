package com.sneaklife.pms.service.common;

import java.util.List;
import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/11/28 12:14
 */
public interface SelectTreeService {

    /**
     * 获取树形选择数据
     * RightSelectTreeServiceImp：带权限
     * LeftSelectTreeServiceImp：不带权限
     * @param map parameter 条件参数
     * @return List<Map<String,Object>>  树形选择数据
     */
    List<Map<String,Object>> selectTree(Map<String, Object> map) throws Exception;
}
