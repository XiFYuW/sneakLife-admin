package com.sneaklife.dao.system.business.businessFunction.functionButton;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface FunctionButtonMapper {

    Integer insertFunctionButton(Map<String, Object> map);

    Integer updateFunctionButton(Map<String, Object> map);

    Integer deleteFunctionButton(Map<String, Object> map);
}
