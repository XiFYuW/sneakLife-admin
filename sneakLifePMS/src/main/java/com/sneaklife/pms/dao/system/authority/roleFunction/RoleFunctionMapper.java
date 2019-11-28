package com.sneaklife.pms.dao.system.authority.roleFunction;

import com.sneaklife.pms.entity.RoleFunction;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
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

    List<RoleFunction> getByIsDel(@Param("isDel") int isDel);

    List<Map<String,Object>> getGroupByRoleId(@Param("roleId") String roleId);

    Integer deleteByRoleId(@Param("roleId") String roleId);

    Integer insertBatch(@Param("list") List<Map<String, Object>> list, @Param("roleId") String roleId);

    RoleFunction getById(@Param("roleId") String roleId);
}
