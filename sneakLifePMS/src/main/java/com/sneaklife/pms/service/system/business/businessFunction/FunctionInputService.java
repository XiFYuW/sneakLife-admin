package com.sneaklife.pms.service.system.business.businessFunction;

import com.sneaklife.util.code.page.PageInfo;
import org.springframework.http.ResponseEntity;

import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/8/21 10:25
 */
public interface FunctionInputService {
    /**
     * Building content bodies
     * @param map parameter
     * @return ResponseEntity<String>
     */
    ResponseEntity<String> functionInput(Map<String, Object> map);

    /**
     * Gets the table list menu
     * @param map parameter
     * @return ResponseEntity<String>
     */
    ResponseEntity<String> functionInputTableView(Map<String, Object> map);

    /**
     * Gets function input data
     * @param map parameter
     * @return ResponseEntity<String>
     */
    ResponseEntity<String> getFunctionInput(Map<String, Object> map, PageInfo pageInfo) throws Exception;

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
