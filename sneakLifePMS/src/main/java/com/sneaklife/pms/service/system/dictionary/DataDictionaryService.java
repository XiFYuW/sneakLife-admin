package com.sneaklife.pms.service.system.dictionary;

import com.sneaklife.pms.entity.modal.TableOpera;
import com.sneaklife.ut.page.PageInfo;

import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 */
public interface DataDictionaryService {

    /**
     * Insert data dictionary
     * @param map parameter
     * @throws Exception
     */
    void insertDataDictionary(Map<String, Object> map) throws Exception;

    /**
     * Paging to get data dictionary data
     * @param map parameter
     * @param pageInfo Paging object
     * @return Map
     * @throws Exception
     */
    Map<String,Object> getDataDictionary(Map<String, Object> map, PageInfo pageInfo) throws Exception;

    /**
     * Building content bodies
     * @param map parameter
     * @return TableOpera
     */
    TableOpera dataDictionary(Map<String, Object> map);

    /**
     * Update data dictionary
     * @param map parameter
     * @throws Exception
     */
    void updateDataDictionary(Map<String, Object> map) throws Exception;

    /**
     * Delete data dictionary
     * @param map parameter
     * @throws Exception
     */
    void deleteDataDictionary(Map<String, Object> map) throws Exception;

    /**
     * Gets the data dictionary details to assemble the drop-down list data
     * @param map parameter
     * @return Map<String, Object>
     */
    Map<String, Object> getByType(Map<String, Object> map);
}
