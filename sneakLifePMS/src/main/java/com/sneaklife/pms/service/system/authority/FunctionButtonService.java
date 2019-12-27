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
public interface FunctionButtonService {
    /**
     * Building content bodies
     * @param map parameter
     * @return List<Map<String,Object>>
     */
    List<Map<String,Object>> functionButton(Map<String, Object> map);

    /**
     * Gets the table list menu
     * @param map parameter
     * @return TableOpera
     */
    TableOpera functionButtonTableView(Map<String, Object> map) throws Exception;

    /**
     * Gets function button data
     * @param map parameter
     * @return Map<String,Object>
     */
    Map<String,Object> getFunctionButton(Map<String, Object> map, PageInfo pageInfo) throws Exception;

    /**
     * Insert function button
     * @param map parameter
     * @throws Exception
     */
    void insertFunctionButton(Map<String, Object> map) throws Exception;

    /**
     * Update function button
     * @param map parameter
     * @throws Exception
     */
    void updateFunctionButton(Map<String, Object> map) throws Exception;

    /**
     * Delete function button
     * @param map parameter
     * @throws Exception
     */
    void deleteFunctionButton(Map<String, Object> map) throws Exception;
}
