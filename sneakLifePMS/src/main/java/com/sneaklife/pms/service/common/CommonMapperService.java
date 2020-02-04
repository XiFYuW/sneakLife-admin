package com.sneaklife.pms.service.common;

import com.sneaklife.pms.entity.modal.TableOpera;
import com.sneaklife.ut.page.PageInfo;

import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/1/26 12:50
 */
public interface CommonMapperService {

    /**
     * Insert
     * @param map parameter
     * @throws Exception
     */
    void insert(Map<String, Object> map) throws Exception;

    /**
     * Paging to get data
     * @param map parameter
     * @param pageInfo Paging object
     * @return Map
     * @throws Exception
     */
    Map<String,Object> getData(Map<String, Object> map, PageInfo pageInfo) throws Exception;

    /**
     * Building content bodies
     * @param map parameter
     * @return TableOpera
     */
    TableOpera buildData(Map<String, Object> map) throws Exception;

    /**
     * Update
     * @param map parameter
     * @throws Exception
     */
    void update(Map<String, Object> map) throws Exception;

    /**
     * Delete
     * @param map parameter
     * @throws Exception
     */
    void delete(Map<String, Object> map) throws Exception;
}
