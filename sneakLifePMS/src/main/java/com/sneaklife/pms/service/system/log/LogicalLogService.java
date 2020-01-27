package com.sneaklife.pms.service.system.log;

import com.sneaklife.pms.entity.modal.TableOpera;
import com.sneaklife.ut.page.PageInfo;

import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 */
public interface LogicalLogService {

    /**
     * Insert data dictionary
     * @param map parameter
     * @throws Exception
     */
    void insertLogicalLog(Map<String, Object> map) throws Exception;

    /**
     * Paging to get data dictionary data
     * @param map parameter
     * @param pageInfo Paging object
     * @return Map
     * @throws Exception
     */
    Map<String,Object> getLogicalLog(Map<String, Object> map, PageInfo pageInfo) throws Exception;

    /**
     * Building content bodies
     * @param map parameter
     * @return TableOpera
     */
    TableOpera logicalLog(Map<String, Object> map) throws Exception;

    /**
     * Update data dictionary
     * @param map parameter
     * @throws Exception
     */
    void updateLogicalLog(Map<String, Object> map) throws Exception;

    /**
     * Delete data dictionary
     * @param map parameter
     * @throws Exception
     */
    void deleteLogicalLog(Map<String, Object> map) throws Exception;

}
