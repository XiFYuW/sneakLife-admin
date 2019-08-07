package com.sneaklife.service.system.authority;

import org.springframework.http.ResponseEntity;

import java.util.Map;
/**
 * @author https://github.com/XiFYuW
 */
public interface FunctionConfigService {

    /**
     * Building content bodies
     * @param map parameter
     * @return ResponseEntity<String>
     */
    ResponseEntity<String> functionConfig(Map<String,Object> map);

    /**
     * Gets the tree list menu
     * @param map parameter
     * @return ResponseEntity<String>
     */
    ResponseEntity<String> functionConfigTreeView(Map<String,Object> map);

    /**
     * Gets function configuration data
     * @param map parameter
     * @return ResponseEntity<String>
     */
    ResponseEntity<String> getFunctionConfig(Map<String,Object> map);

    /**
     * Insert function configuration
     * @param map parameter
     * @throws Exception
     */
    void insertFunctionConfig(Map<String,Object> map) throws Exception;

    /**
     * Update function configuration
     * @param map parameter
     * @throws Exception
     */
    void updateFunctionConfig(Map<String,Object> map) throws Exception;

    /**
     * Delete function configuration
     * @param map parameter
     * @throws Exception
     */
    void deleteFunctionConfig(Map<String,Object> map) throws Exception;
}
