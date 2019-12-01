package com.sneaklife.pms.service.common;

import com.sneaklife.pms.entity.RoleFunction;
import com.sneaklife.pms.entity.SystemMenu;
import com.sneaklife.pms.entity.modal.TableOpera;

import java.util.List;
import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 */
public interface OperaService {

    /**
     * Building content bodies
     * @param map parameter
     * @param is Whether to cut with length 2 for the list
     * @return Functional authority subject
     */
    TableOpera buildOperaBody(Map<String, Object> map, boolean is);

    /**
     * Build functional tree data
     * @param map parameter
     * @return tree data，Id as the current node and pid as the parent node
     */
    List<Map<String,Object>> buildOperaTreeGrid(Map<String, Object> map);

    /**
     *  Clear global variables，Size, data for OperaServiceIml
     */
    void clean();

    /**
     * Build the role function tree data
     * @param roleFunction Build role function information
     * @map Request parameters
     * @return tree data，Id as the current node and pid as the parent node
     */
    List<Map<String, Object>> buildRoleFunction(RoleFunction roleFunction, Map<String, Object> map);

    List<Map<String, Object>> getSelectsKyByMenuId(String menuId, String htmlType);

    int removeNode(SystemMenu parentMenu, List<SystemMenu> list, int size);
}
