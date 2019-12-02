package com.sneaklife.pms.service.system.dictionary;

import com.sneaklife.ut.page.PageInfo;
import org.springframework.http.ResponseEntity;

import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 */
public interface TypeDictionaryService {

    /**
     * Insert type dictionary
     * @param map parameter
     * @throws Exception
     */
    void insertTypeDictionary(Map<String, Object> map) throws Exception;

    /**
     * Paging to get type dictionary data
     * @param map parameter
     * @param pageInfo Paging object
     * @return ResponseEntity<String>
     * @throws Exception
     */
    ResponseEntity<String> getTypeDictionary(Map<String, Object> map, PageInfo pageInfo) throws Exception;

    /**
     * Building content bodies
     * @param map parameter
     * @return ResponseEntity<String>
     * @throws Exception
     */
    ResponseEntity<String> typeDictionary(Map<String, Object> map) throws Exception;

    /**
     * Update type dictionary
     * @param map parameter
     * @throws Exception
     */
    void updateTypeDictionary(Map<String, Object> map) throws Exception;

    /**
     * Delete type dictionary
     * @param map parameter
     * @throws Exception
     */
    void deleteTypeDictionary(Map<String, Object> map) throws Exception;
}
