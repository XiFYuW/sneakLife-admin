package com.sneaklife.pms.service.common;

import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/12/1 12:27
 */
public interface SelectTreeViewService {

    /**
     * Gets the system function menu data to assemble the drop-down list tree data
     * @param map parameter
     * @return Map<String, Object>
     */
    Map<String, Object> selectTreeView(Map<String, Object> map);
}
