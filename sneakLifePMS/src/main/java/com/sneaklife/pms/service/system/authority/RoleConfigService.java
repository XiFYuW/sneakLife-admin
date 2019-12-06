package com.sneaklife.pms.service.system.authority;

import com.sneaklife.pms.entity.modal.TableOpera;
import com.sneaklife.ut.page.PageInfo;
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
     * @return Map<String, Object>
     * @throws Exception
     */
    Map<String, Object> getRoleConfig(Map<String, Object> map, PageInfo pageInfo) throws Exception;

    /**
     * Building content bodies
     * @param map parameter
     * @return TableOpera
     * @throws Exception
     */
    TableOpera roleConfig(Map<String, Object> map) throws Exception;

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

    /**
     * Secondary page construction
     * @return List<Map<String, Object>>
     */
    List<Map<String, Object>> buildRoleTreeView();

    /**
     * Gets the role drop-down list data
     * @param map parameter
     * @return Map<String,Object>
     */
    Map<String,Object> selectsList(Map<String, Object> map);
}
