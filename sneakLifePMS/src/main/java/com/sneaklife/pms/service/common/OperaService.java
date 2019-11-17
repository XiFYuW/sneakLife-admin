package com.sneaklife.pms.service.common;

import com.sneaklife.pms.entity.OperaSb;
import com.sneaklife.pms.entity.RoleFunction;
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
     * Insert function options
     * @param operaSb parameter
     * @throws Exception Global exceptions are handled
     */
    void insertOperaSb(OperaSb operaSb) throws Exception;

    /**
     * Update function options
     * @param map parameter
     * @throws Exception Global exceptions are handled
     */
    void updateOpera(Map<String, Object> map) throws Exception;

    /**
     * Delete function options
     * @param map parameter
     * @throws Exception Global exceptions are handled
     */
    void deleteOpera(Map<String, Object> map) throws Exception;

    /**
     * Build the role function tree data
     * @param roleFunction Build role function information
     * @map Request parameters
     * @return tree data，Id as the current node and pid as the parent node
     */
    List<Map<String, Object>> buildRoleFunction(RoleFunction roleFunction, Map<String, Object> map);
}
