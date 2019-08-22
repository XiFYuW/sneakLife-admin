package com.sneaklife.dao.system.business.businessFunction.fcuntionColumns;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface FunctionColumnsMapper {

    Integer insertFunctionColumns(Map<String, Object> map);

    Integer updateFunctionColumns(Map<String, Object> map);

    Integer deleteFunctionColumns(Map<String, Object> map);
}
