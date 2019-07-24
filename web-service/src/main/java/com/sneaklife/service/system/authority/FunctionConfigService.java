package com.sneaklife.service.system.authority;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface FunctionConfigService {

    ResponseEntity<String> functionConfig(Map<String,Object> map);

    ResponseEntity<String> functionConfigTreeView(Map<String,Object> map);

    ResponseEntity<String> getFunctionConfig(Map<String,Object> map);

    void insertFunctionConfig(Map<String,Object> map) throws Exception;

    void updateFunctionConfig(Map<String,Object> map) throws Exception;

    void deleteFunctionConfig(Map<String,Object> map) throws Exception;
}
