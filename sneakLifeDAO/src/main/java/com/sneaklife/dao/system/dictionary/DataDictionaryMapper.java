package com.sneaklife.dao.system.dictionary;

import com.sneaklife.dao.entity.DataDictionary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface DataDictionaryMapper {

    Integer insertDataDictionary(Map<String, Object> map);

    Integer updateDataDictionary(Map<String, Object> map);

    Integer deleteDataDictionary(Map<String, Object> map);

    List<DataDictionary> getByType(@Param("types") String[] types);
}
