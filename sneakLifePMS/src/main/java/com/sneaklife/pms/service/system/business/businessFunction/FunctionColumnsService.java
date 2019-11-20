package com.sneaklife.pms.service.system.business.businessFunction;

import com.sneaklife.ut.page.PageInfo;
import org.springframework.http.ResponseEntity;

import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/8/21 10:25
 */
public interface FunctionColumnsService {
    /**
     * Building content bodies
     * @param map parameter
     * @return ResponseEntity<String>
     */
    ResponseEntity<String> functionColumns(Map<String, Object> map);

    /**
     * Gets the table list menu
     * @param map parameter
     * @return ResponseEntity<String>
     */
    ResponseEntity<String> functionColumnsTableView(Map<String, Object> map);

    /**
     * Gets function input data
     * @param map parameter
     * @return ResponseEntity<String>
     */
    ResponseEntity<String> getFunctionColumns(Map<String, Object> map, PageInfo pageInfo) throws Exception;

    /**
     * Insert function columns
     * @param map parameter
     * @throws Exception
     */
    void insertFunctionColumns(Map<String, Object> map) throws Exception;

    /**
     * Update function columns
     * @param map parameter
     * @throws Exception
     */
    void updateFunctionColumns(Map<String, Object> map) throws Exception;

    /**
     * Delete function columns
     * @param map parameter
     * @throws Exception
     */
    void deleteFunctionColumns(Map<String, Object> map) throws Exception;
}
