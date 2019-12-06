package com.sneaklife.pms.service.system.dictionary;

import com.sneaklife.pms.entity.modal.TableOpera;
import com.sneaklife.ut.page.PageInfo;

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
     * @return Map
     * @throws Exception
     */
    Map<String,Object> getTypeDictionary(Map<String, Object> map, PageInfo pageInfo) throws Exception;

    /**
     * Building content bodies
     * @param map parameter
     * @return TableOpera
     */
    TableOpera typeDictionary(Map<String, Object> map);

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
