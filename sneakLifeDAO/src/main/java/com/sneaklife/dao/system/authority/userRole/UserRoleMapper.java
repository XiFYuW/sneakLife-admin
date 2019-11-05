package com.sneaklife.dao.system.authority.userRole;


import com.sneaklife.dao.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/8/4 10:07
 */
@Mapper
public interface UserRoleMapper {

    Integer insertUserRole(Map<String, Object> map);

    Integer updateUserRole(Map<String, Object> map);

    Integer deleteUserRole(Map<String, Object> map);

    UserRole getAllNameById(UserRole userRole);

    Integer updateBatch(List<Map<String,Object>> list);

}
