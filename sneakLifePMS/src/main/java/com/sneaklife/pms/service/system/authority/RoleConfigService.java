package com.sneaklife.pms.service.system.authority;

import com.sneaklife.pms.service.common.CommonMapperService;

import java.util.List;
import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/8/4 10:00
 */
public interface RoleConfigService extends CommonMapperService {

    /**
     * Secondary page construction
     * @return List<Map<String, Object>>
     */
    List<Map<String, Object>> buildRoleTreeView();

    /**
     * Gets the role drop-down list data
     * @param map parameter
     * @return Map<String,Object>
     */
    Map<String,Object> selectsList(Map<String, Object> map);
}
