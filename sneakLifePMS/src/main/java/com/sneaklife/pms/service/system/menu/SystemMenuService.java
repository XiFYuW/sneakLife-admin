package com.sneaklife.pms.service.system.menu;

import com.sneaklife.pms.entity.SystemMenu;
import com.sneaklife.pms.entity.modal.TableOpera;
import com.sneaklife.ut.page.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 */
public interface SystemMenuService {

    /**
     * Get mappers.mappers menu
     * @return List
     */
    List<SystemMenu> getMenu();

    /**
     * Paging to get data dictionary data
     * @param map parameter
     * @param pageInfo Paging object
     * @return Map<String, Object>
     * @throws Exception
     */
    Map<String, Object> getSystemFunctionMenu(Map<String, Object> map, PageInfo pageInfo) throws Exception;

    /**
     * Building content bodies
     * @param map parameter
     * @return TableOpera
     * @throws Exception
     */
    TableOpera systemFunctionMenu(Map<String, Object> map) throws Exception;

    /**
     * Insert data system function menu
     * @param map parameter
     * @throws Exception
     */
    void insertSystemFunctionMenu(Map<String, Object> map) throws Exception;

    /**
     * Update data system function menu
     * @param map parameter
     * @throws Exception
     */
    void updateSystemFunctionMenu(Map<String, Object> map) throws Exception;

    /**
     * Delete data system function menu
     * @param map parameter
     * @throws Exception
     */
    void deleteSystemFunctionMenu(Map<String, Object> map) throws Exception;

    /**
     * Gets the system function menu data to assemble the drop-down list tree data
     * @param map parameter
     * @return Map<String, Object>
     */
    Map<String, Object> selectTreeView(Map<String, Object> map);

}
