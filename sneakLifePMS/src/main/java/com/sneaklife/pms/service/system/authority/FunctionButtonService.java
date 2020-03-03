package com.sneaklife.pms.service.system.authority;

import com.sneaklife.pms.service.common.CommonMapperService;

import java.util.List;
import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/8/21 10:25
 */
public interface FunctionButtonService extends CommonMapperService {
    /**
     * 构建左面页面列表树
     * @param map 条件参数
     * @return List<Map<String,Object>>
     * @throws Exception 条件异常信息
     */
    List<Map<String,Object>> functionButton(Map<String, Object> map) throws Exception;
}
