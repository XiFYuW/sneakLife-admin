package com.sneaklife.dao.system.roleFunction;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/8/4 10:07
 */
@Mapper
public interface RoleFunctionMapper {

    Integer insertRoleFunction(Map<String, Object> map);

    Integer updateRoleFunction(Map<String, Object> map);

    Integer deleteRoleFunction(Map<String, Object> map);
}
