package com.sneaklife.pms.service.system.menu;

import com.sneaklife.pms.entity.SystemMenu;
import com.sneaklife.pms.service.common.CommonMapperService;

import java.util.List;
import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 */
public interface SystemMenuService extends CommonMapperService {

    /**
     * Get mappers.mappers menu
     * @return List
     * @throws Exception
     */
    List<SystemMenu> getMenu() throws Exception;

    /**
     * Gets the system function menu data to assemble the drop-down list tree data
     * @param map parameter
     * @return Map<String, Object>
     */
    Map<String, Object> selectTreeView(Map<String, Object> map);

}
