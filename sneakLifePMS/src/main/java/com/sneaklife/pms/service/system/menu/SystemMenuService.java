package com.sneaklife.pms.service.system.menu;

import com.sneaklife.pms.service.common.CommonMapperService;

import java.util.List;
import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 */
public interface SystemMenuService extends CommonMapperService {

    /**
     * 获取功能菜单
     * @return List
     * @throws Exception 异常提示
     */
    List<Map<String,Object>> getMenu() throws Exception;

    /**
     * 获取用于组装下拉列表树数据的系统功能菜单数据
     * @param map 条件参数
     * @return Map<String, Object>
     */
    Map<String, Object> selectTreeView(Map<String, Object> map);

    void logOut() throws Exception;

}
