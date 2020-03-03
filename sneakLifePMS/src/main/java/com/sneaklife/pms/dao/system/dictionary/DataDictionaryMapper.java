package com.sneaklife.pms.dao.system.dictionary;

import com.sneaklife.pms.dao.CommonDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface DataDictionaryMapper extends CommonDao {

    /**
     * 根据数据字典类型id获取数据字典数据对
     * @param typeId 数据字典类型id
     * @return List<Map<String,Object>>
     */
    List<Map<String,Object>> getNameValueByTypeId(@Param("typeId") Long typeId);

}
