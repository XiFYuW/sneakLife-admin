package com.sneaklife.pms.dao.system.authority.roleFunction;

import com.sneaklife.pms.dao.CommonDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/8/4 10:07
 */
@Mapper
public interface RoleFunctionMapper extends CommonDao {

    /**
     * 获取功能菜单选项
     * @param roleId 角色id
     * @return List<Map<String,Object>>
     */
    List<Map<String,Object>> getByRoleId(@Param("roleId") String roleId);

    /**
     * 获取具体功能选项
     * @param roleId 角色id
     * @return List<Map<String,Object>>
     */
    List<Map<String,Object>> getSpByRoleId(@Param("roleId") String roleId);

    /**
     * 批量删除功能菜单选项
     * @param list 添加的数据项
     * @param roleId 角色id
     * @return Integer
     */
    Integer deleteBatch(@Param("list") List<String> list, @Param("roleId") String roleId);

    /**
     * 批量删除具体功能选项
     * @param list 添加的数据项
     * @param roleId 角色id
     * @return Integer
     */
    Integer deleteSpBatch(@Param("list") List<String> list, @Param("roleId") String roleId);

    /**
     * 批量添加功能菜单数据
     * @param list 添加的数据项
     * @param roleId 角色id
     * @return Integer
     */
    Integer insertBatch(@Param("list") List<String> list, @Param("roleId") String roleId);

    /**
     * 批量添加具体功能数据
     * @param list 添加的数据项
     * @param roleId 角色id
     * @return Integer
     */
    Integer insertSpBatch(@Param("list") List<String> list, @Param("roleId") String roleId);
}
