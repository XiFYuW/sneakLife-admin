package com.sneaklife.pms.service.system.authority;

import com.sneaklife.pms.entity.modal.TableOpera;
import com.sneaklife.ut.page.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/8/21 10:25
 */
public interface FunctionBoService {
    /**
     * Building content bodies
     * @param map parameter
     * @return  List<Map<String, Object>>
     */
    List<Map<String, Object>> functionBo(Map<String, Object> map);

    /**
     * Gets the table list menu
     * @param map parameter
     * @return TableOpera
     */
    TableOpera functionBoTableView(Map<String, Object> map) throws Exception;

    /**
     * Gets function bo data
     * @param map parameter
     * @return Map<String,Object>
     */
    Map<String,Object> getFunctionBo(Map<String, Object> map, PageInfo pageInfo) throws Exception;

    /**
     * Insert function bo
     * @param map parameter
     * @throws Exception
     */
    void insertFunctionBo(Map<String, Object> map) throws Exception;

    /**
     * Update function bo
     * @param map parameter
     * @throws Exception
     */
    void updateFunctionBo(Map<String, Object> map) throws Exception;

    /**
     * Delete function bo
     * @param map parameter
     * @throws Exception
     */
    void deleteFunctionBo(Map<String, Object> map) throws Exception;
}
