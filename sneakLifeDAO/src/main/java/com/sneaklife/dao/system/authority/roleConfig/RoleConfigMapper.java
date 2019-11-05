package com.sneaklife.dao.system.authority.roleConfig;

import com.sneaklife.dao.entity.RoleConfig;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/8/4 10:07
 */
@Mapper
public interface RoleConfigMapper {

    Integer insertRoleConfig(Map<String, Object> map);

    Integer updateRoleConfig(Map<String, Object> map);

    Integer deleteRoleConfig(Map<String, Object> map);

    List<RoleConfig> getByIsDel(@Param("isDel") int isDel);

    List<Map<String,Object>> getByIsDelMap(@Param("isDel") int isDel);

    RoleConfig getById(@Param("id") String id);
}
