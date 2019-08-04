package com.sneaklife.service.system.role;

import com.sneaklife.page.PageInfo;
import org.springframework.http.ResponseEntity;

import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/8/4 10:00
 */
public interface RoleService {
    /**
     * Insert role
     * @param map parameter
     * @throws Exception
     */
    void insertRole(Map<String,Object> map) throws Exception;

    /**
     * Paging to get role data
     * @param map parameter
     * @param pageInfo Paging object
     * @return ResponseEntity<String>
     * @throws Exception
     */
    ResponseEntity<String> getRole(Map<String,Object> map, PageInfo pageInfo) throws Exception;

    /**
     * Building content bodies
     * @param map parameter
     * @return ResponseEntity<String>
     * @throws Exception
     */
    ResponseEntity<String> role(Map<String,Object> map) throws Exception;

    /**
     * Update role
     * @param map parameter
     * @throws Exception
     */
    void updateRole(Map<String,Object> map) throws Exception;

    /**
     * Delete role
     * @param map parameter
     * @throws Exception
     */
    void deleteRole(Map<String,Object> map) throws Exception;
}
