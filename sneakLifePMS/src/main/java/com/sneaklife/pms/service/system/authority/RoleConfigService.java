package com.sneaklife.pms.service.system.authority;

import com.sneaklife.ut.code.page.PageInfo;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/8/4 10:00
 */
public interface RoleConfigService {
    /**
     * Insert roleConfig
     * @param map parameter
     * @throws Exception
     */
    void insertRoleConfig(Map<String, Object> map) throws Exception;

    /**
     * Paging to get roleConfig data
     * @param map parameter
     * @param pageInfo Paging object
     * @return ResponseEntity<String>
     * @throws Exception
     */
    ResponseEntity<String> getRoleConfig(Map<String, Object> map, PageInfo pageInfo) throws Exception;

    /**
     * Building content bodies
     * @param map parameter
     * @return ResponseEntity<String>
     * @throws Exception
     */
    ResponseEntity<String> roleConfig(Map<String, Object> map) throws Exception;

    /**
     * Update roleConfig
     * @param map parameter
     * @throws Exception
     */
    void updateRoleConfig(Map<String, Object> map) throws Exception;

    /**
     * Delete roleConfig
     * @param map parameter
     * @throws Exception
     */
    void deleteRoleConfig(Map<String, Object> map) throws Exception;

    List<Map<String, Object>> buildRoleTreeView();

    ResponseEntity<String> selectsList(Map<String, Object> map);
}
