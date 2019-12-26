package com.sneaklife.pms.service.common;

import java.util.List;
import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/11/28 12:14
 */
public interface LeftSelectViewService {

    /**
     * Left drop-down list tree data
     * @param map parameter
     * @return List<Map<String,Object>>
     */
    List<Map<String,Object>> leftSelectsView(Map<String, Object> map);
}
