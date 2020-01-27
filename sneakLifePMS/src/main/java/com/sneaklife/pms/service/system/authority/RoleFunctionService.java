package com.sneaklife.pms.service.system.authority;

import com.sneaklife.pms.service.common.CommonInterfaceService;

import java.util.List;
import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 */
public interface RoleFunctionService extends CommonInterfaceService {

    /**
     * Building content bodies
     * @param map parameter
     * @return List<Map<String,Object>>
     */
    List<Map<String,Object>> roleFunction(Map<String, Object> map);

    /**
     * Gets roleFunction function data
     * @param map parameter
     * @return List<Map<String,Object>>
     */
    List<Map<String,Object>> getRoleFunction(Map<String, Object> map);
}
