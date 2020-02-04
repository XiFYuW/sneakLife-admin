package com.sneaklife.pms.service.system.authority;

import com.sneaklife.pms.service.common.CommonMapperService;

import java.util.List;
import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/8/21 10:25
 */
public interface FunctionInputService extends CommonMapperService {
    /**
     * Building content bodies
     * @param map parameter
     * @return  List<Map<String, Object>>
     */
    List<Map<String, Object>> functionInput(Map<String, Object> map);
}
