package com.sneaklife.pms.dao.system.dictionary;

import com.sneaklife.pms.dao.CommonDao;
import com.sneaklife.pms.entity.DataDictionary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface DataDictionaryMapper extends CommonDao {

    List<DataDictionary> getByType(@Param("types") String[] types);

    List<Map<String,Object>> getNameValueByTypeId(@Param("typeId") Long typeId);

}
