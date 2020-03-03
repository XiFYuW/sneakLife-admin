package com.sneaklife.pms.service.system.authority;

import com.sneaklife.pms.service.common.CommonMapperService;

import java.util.List;
import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 */
public interface RoleFunctionService extends CommonMapperService {

    /**
     * 获取构建角色列表树
     * @param map 条件参数
     * @return List<Map<String,Object>>
     */
    List<Map<String,Object>> roleFunction(Map<String, Object> map);

    /**
     * 获取具体功能选项
     * @param map 条件参数
     * @return List<Map<String,Object>>
     * @throws Exception 异常提示信息
     */
    List<Map<String,Object>> getRoleFunction(Map<String, Object> map) throws Exception;

    /**
     * 获取功能菜单下拉树（带权限）
     * @param map 条件参数
     * @return List<Map<String,Object>>
     * @throws Exception 异常提示信息
     */
    List<Map<String,Object>> roleFunctionTreeView(Map<String, Object> map) throws Exception;

    /**
     * 更新具体功能选项
     * @param map 条件参数
     * @throws Exception 异常提示信息
     */
    void updateSpRoleFunction(Map<String, Object> map) throws Exception;
}
