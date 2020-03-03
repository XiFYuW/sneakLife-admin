package com.sneaklife.pms.dao.system.authority.opera;

import com.sneaklife.pms.dao.CommonDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 */
@Mapper
public interface OperaSbMapper extends CommonDao {

    /**
     * 查询功能按钮
     * @param map 提交参数
     * @return List<Map<String,Object>>
     */
    List<Map<String,Object>> findOperaSb(Map<String, Object> map);
}
