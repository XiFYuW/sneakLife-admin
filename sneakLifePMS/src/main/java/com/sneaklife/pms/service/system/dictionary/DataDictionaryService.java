package com.sneaklife.pms.service.system.dictionary;

import com.sneaklife.pms.service.common.CommonInterfaceService;

import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 */
public interface DataDictionaryService extends CommonInterfaceService {

    /**
     * Gets the data dictionary details to assemble the drop-down list data
     * @param map parameter
     * @return Map<String, Object>
     */
    Map<String, Object> getByType(Map<String, Object> map);
}
