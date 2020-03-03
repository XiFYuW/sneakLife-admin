package com.sneaklife.pms.service.system.authority;

import com.sneaklife.pms.service.common.CommonMapperService;

import java.util.List;
import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/8/4 10:00
 */
public interface RoleConfigService extends CommonMapperService {

    /**
     * 构建角色列表树
     * @return List<Map<String, Object>>
     */
    List<Map<String, Object>> buildRoleTreeView();

    /**
     * 获取下拉列表数据
     * @param map 条件参数
     * @return Map<String,Object>
     */
    Map<String,Object> selectsList(Map<String, Object> map);
}
