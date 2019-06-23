package com.sneaklife.dao.system.dictionary;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface DataDictionaryMapper{

    Integer insertDataDictionary(Map<String, Object> map);
}
