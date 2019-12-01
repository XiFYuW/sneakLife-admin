package com.sneaklife.pms.service.system.menu;

import com.sneaklife.ut.page.PageInfo;
import org.springframework.http.ResponseEntity;

import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 */
public interface SystemMenuService {

    /**
     * Get mappers.mappers menu
     * @return ResponseEntity<String>
     */
    ResponseEntity<String> getMenu();

    /**
     * Paging to get data dictionary data
     * @param map parameter
     * @param pageInfo Paging object
     * @return ResponseEntity<String>
     * @throws Exception
     */
    ResponseEntity<String> getSystemFunctionMenu(Map<String, Object> map, PageInfo pageInfo) throws Exception;

    /**
     * Building content bodies
     * @param map parameter
     * @return ResponseEntity<String>
     * @throws Exception
     */
    ResponseEntity<String> systemFunctionMenu(Map<String, Object> map) throws Exception;

    /**
     * Insert data system function menu
     * @param map parameter
     * @throws Exception
     */
    void insertSystemFunctionMenu(Map<String, Object> map) throws Exception;

    /**
     * Update data system function menu
     * @param map parameter
     * @throws Exception
     */
    void updateSystemFunctionMenu(Map<String, Object> map) throws Exception;

    /**
     * Delete data system function menu
     * @param map parameter
     * @throws Exception
     */
    void deleteSystemFunctionMenu(Map<String, Object> map) throws Exception;

    ResponseEntity<String> selectTreeView(Map<String, Object> map);

}
