package com.sneaklife.service.system.dictionary;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface DataDictionaryService {

    ResponseEntity<String> insertDataDictionary(Map<String,Object> map);

    ResponseEntity<String> getDataDictionary(Map<String,Object> map);

    ResponseEntity<String> dataDictionary(Map<String,Object> map);
}
