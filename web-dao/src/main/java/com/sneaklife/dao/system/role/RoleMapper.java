package com.sneaklife.dao.system.role;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/8/4 10:07
 */
@Mapper
public interface RoleMapper {

    Integer insertRole(Map<String, Object> map);

    Integer updateRole(Map<String, Object> map);

    Integer deleteRole(Map<String, Object> map);
}
