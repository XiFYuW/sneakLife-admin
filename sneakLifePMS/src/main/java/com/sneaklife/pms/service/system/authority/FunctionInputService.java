package com.sneaklife.pms.service.system.authority;

import com.sneaklife.pms.entity.modal.TableOpera;
import com.sneaklife.ut.page.PageInfo;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/8/21 10:25
 */
public interface FunctionInputService {
    /**
     * Building content bodies
     * @param map parameter
     * @return  List<Map<String, Object>>
     */
    List<Map<String, Object>> functionInput(Map<String, Object> map);

    /**
     * Gets the table list menu
     * @param map parameter
     * @return TableOpera
     */
    TableOpera functionInputTableView(Map<String, Object> map) throws Exception;

    /**
     * Gets function input data
     * @param map parameter
     * @return Map<String,Object>
     */
    Map<String,Object> getFunctionInput(Map<String, Object> map, PageInfo pageInfo) throws Exception;

    /**
     * Insert function input
     * @param map parameter
     * @throws Exception
     */
    void insertFunctionInput(Map<String, Object> map) throws Exception;

    /**
     * Update function input
     * @param map parameter
     * @throws Exception
     */
    void updateFunctionInput(Map<String, Object> map) throws Exception;

    /**
     * Delete function input
     * @param map parameter
     * @throws Exception
     */
    void deleteFunctionInput(Map<String, Object> map) throws Exception;
}
