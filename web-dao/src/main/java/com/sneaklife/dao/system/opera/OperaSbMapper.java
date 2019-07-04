package com.sneaklife.dao.system.opera;

import com.sneaklife.dao.entity.OperaSb;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface OperaSbMapper {

    List<OperaSb> findOperaSbByShow(Map<String,Object> map);
}
