package com.sneaklife.pms.service.system.dictionary;

import com.sneaklife.pms.service.common.CommonMapperService;

import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 */
public interface DataDictionaryService extends CommonMapperService {

    /**
     * Gets the data dictionary details to assemble the drop-down list data
     * @param map parameter
     * @return Map<String, Object>
     */
    Map<String, Object> getByType(Map<String, Object> map);
}
