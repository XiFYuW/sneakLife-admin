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
     * Gets role function data
     * @param map parameter
     * @return ResponseEntity<String>
     */
    ResponseEntity<String> getRoleFunction(Map<String, Object> map);

    /**
     * Insert role function configuration
     * @param map parameter
     * @throws Exception
     */
    void insertRoleFunction(Map<String, Object> map) throws Exception;

    /**
     * Update role function configuration
     * @param map parameter
     * @throws Exception
     */
    void updateRoleFunction(Map<String, Object> map) throws Exception;

    /**
     * Delete role function configuration
     * @param map parameter
     * @throws Exception
     */
    void deleteRoleFunction(Map<String, Object> map) throws Exception;
}
