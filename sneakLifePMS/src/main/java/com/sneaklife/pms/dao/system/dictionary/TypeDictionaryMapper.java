package com.sneaklife.pms.dao.system.dictionary;

import com.sneaklife.pms.dao.CommonDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TypeDictionaryMapper extends CommonDao {

    /**
     * 获取数据字典类型数据对
     * @return List<Map<String,Object>>
     */
    List<Map<String,Object>> getIdName();

}
