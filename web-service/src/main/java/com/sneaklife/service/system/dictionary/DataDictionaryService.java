package com.sneaklife.service.system.dictionary;

import com.sneaklife.exception.SneakLifeException;
import com.sneaklife.page.PageInfo;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface DataDictionaryService {

    void insertDataDictionary(Map<String,Object> map) throws Exception;

    ResponseEntity<String> getDataDictionary(Map<String,Object> map, PageInfo pageInfo) throws Exception;

    ResponseEntity<String> dataDictionary(Map<String,Object> map) throws Exception;

    void updateDataDictionary(Map<String,Object> map) throws Exception;

    void deleteDataDictionary(Map<String,Object> map) throws Exception;
}
