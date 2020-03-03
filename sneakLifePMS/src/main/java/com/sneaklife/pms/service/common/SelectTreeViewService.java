package com.sneaklife.pms.service.common;

import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/12/1 12:27
 */
public interface SelectTreeViewService {

    /**
     * 获取用于组装下拉列表树数据的系统功能菜单数据
     * @param map parameter 条件参数
     * @return Map<String, Object>
     */
    Map<String, Object> selectTreeView(Map<String, Object> map);
}
