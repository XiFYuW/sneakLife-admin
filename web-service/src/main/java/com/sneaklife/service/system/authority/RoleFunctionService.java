package com.sneaklife.service.system.authority;

import org.springframework.http.ResponseEntity;

import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 */
public interface RoleFunctionService {

    /**
     * Building content bodies
     * @param map parameter
     * @return ResponseEntity<String>
     */
    ResponseEntity<String> roleFunction(Map<String, Object> map);

    /**
     * Gets roleFunction function data
     * @param map parameter
     * @return ResponseEntity<String>
     */
    ResponseEntity<String> getRoleFunction(Map<String, Object> map);

    /**
     * Insert roleFunction function configuration
     * @param map parameter
     * @throws Exception
     */
    void insertRoleFunction(Map<String, Object> map) throws Exception;

    /**
     * Update roleFunction function configuration
     * @param map parameter
     * @throws Exception
     */
    void updateRoleFunction(Map<String, Object> map) throws Exception;

    /**
     * Delete roleFunction function configuration
     * @param map parameter
     * @throws Exception
     */
    void deleteRoleFunction(Map<String, Object> map) throws Exception;
}
