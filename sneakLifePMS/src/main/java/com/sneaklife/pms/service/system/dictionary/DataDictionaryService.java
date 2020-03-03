package com.sneaklife.pms.service.system.dictionary;

import com.sneaklife.pms.service.common.CommonMapperService;

import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 */
public interface DataDictionaryService extends CommonMapperService {

    /**
     * 根据类型获取下拉数据字典数据
     * @param map 条件参数
     * @return Map<String, Object>
     */
    Map<String, Object> getByType(Map<String, Object> map);
}
