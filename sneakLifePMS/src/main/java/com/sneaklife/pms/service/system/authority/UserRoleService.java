package com.sneaklife.pms.service.system.authority;

import com.sneaklife.ut.page.PageInfo;
import org.springframework.http.ResponseEntity;

import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 */
public interface UserRoleService {

    /**
     * Building content bodies
     * @param map parameter
     * @return ResponseEntity<String>
     */
    ResponseEntity<String> userRole(Map<String, Object> map);

    /**
     * Gets userRole function data
     * @param map parameter
     * @param pageInfo Paging object
     * @return ResponseEntity<String>
     */
    ResponseEntity<String> getUserRole(Map<String, Object> map, PageInfo pageInfo) throws Exception;

    /**
     * Insert userRole function configuration
     * @param map parameter
     * @throws Exception
     */
    void insertUserRole(Map<String, Object> map) throws Exception;

    /**
     * Update userRole function configuration
     * @param map parameter
     * @throws Exception
     */
    void updateUserRole(Map<String, Object> map) throws Exception;

    /**
     * Delete userRole function configuration
     * @param map parameter
     * @throws Exception
     */
    void deleteUserRole(Map<String, Object> map) throws Exception;
}
