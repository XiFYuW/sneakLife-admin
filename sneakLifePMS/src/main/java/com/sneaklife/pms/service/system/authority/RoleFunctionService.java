package com.sneaklife.pms.service.system.authority;

import com.sneaklife.pms.entity.modal.TableOpera;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 */
public interface RoleFunctionService {

    /**
     * Secondary page construction
     * @param map parameter
     * @return TableOpera
     */
    TableOpera roleFunctionTreeView(Map<String, Object> map);

    /**
     * Building content bodies
     * @param map parameter
     * @return List<Map<String,Object>>
     */
    List<Map<String,Object>> roleFunction(Map<String, Object> map);

    /**
     * Gets roleFunction function data
     * @param map parameter
     * @return List<Map<String,Object>>
     */
    List<Map<String,Object>> getRoleFunction(Map<String, Object> map);

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
