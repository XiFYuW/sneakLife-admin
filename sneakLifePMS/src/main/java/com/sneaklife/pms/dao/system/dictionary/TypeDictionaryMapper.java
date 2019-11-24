package com.sneaklife.pms.dao.system.dictionary;

import com.sneaklife.pms.dao.CommonDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TypeDictionaryMapper extends CommonDao {

    List<Map<String,Object>> getIdName();

}
