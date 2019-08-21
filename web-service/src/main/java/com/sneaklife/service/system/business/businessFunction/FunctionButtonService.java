package com.sneaklife.service.system.business.businessFunction;

import com.sneaklife.page.PageInfo;
import org.springframework.http.ResponseEntity;

import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/8/21 10:25
 */
public interface FunctionButtonService {
    /**
     * Building content bodies
     * @param map parameter
     * @return ResponseEntity<String>
     */
    ResponseEntity<String> functionButton(Map<String,Object> map);

    /**
     * Gets the table list menu
     * @param map parameter
     * @return ResponseEntity<String>
     */
    ResponseEntity<String> functionButtonTableView(Map<String,Object> map);

    /**
     * Gets function button data
     * @param map parameter
     * @return ResponseEntity<String>
     */
    ResponseEntity<String> getFunctionButton(Map<String,Object> map, PageInfo pageInfo) throws Exception;

    /**
     * Insert function button
     * @param map parameter
     * @throws Exception
     */
    void insertFunctionButton(Map<String,Object> map) throws Exception;

    /**
     * Update function button
     * @param map parameter
     * @throws Exception
     */
    void updateFunctionButton(Map<String,Object> map) throws Exception;

    /**
     * Delete function button
     * @param map parameter
     * @throws Exception
     */
    void deleteFunctionButton(Map<String,Object> map) throws Exception;
}
