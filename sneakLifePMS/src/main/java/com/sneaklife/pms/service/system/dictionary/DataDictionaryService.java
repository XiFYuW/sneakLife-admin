package com.sneaklife.pms.service.system.dictionary;

import com.sneaklife.util.code.page.PageInfo;
import org.springframework.http.ResponseEntity;

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
     * @return ResponseEntity<String>
     * @throws Exception
     */
    ResponseEntity<String> getDataDictionary(Map<String, Object> map, PageInfo pageInfo) throws Exception;

    /**
     * Building content bodies
     * @param map parameter
     * @return ResponseEntity<String>
     * @throws Exception
     */
    ResponseEntity<String> dataDictionary(Map<String, Object> map) throws Exception;

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

    ResponseEntity<String> getByType(Map<String, Object> map);
}
