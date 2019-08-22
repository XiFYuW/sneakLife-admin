package com.sneaklife.dao.system.business.businessFunction.functionInput;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface FunctionInputMapper {

    Integer insertFunctionInput(Map<String, Object> map);

    Integer updateFunctionInput(Map<String, Object> map);

    Integer deleteFunctionInput(Map<String, Object> map);
}
