package com.sneaklife.pms.dao.system.authority.opera;

import com.sneaklife.pms.dao.CommonDao;
import com.sneaklife.pms.entity.OperaSb;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 */
@Mapper
public interface OperaSbMapper extends CommonDao {

    List<Map<String,Object>> findOperaSbByShow(Map<String, Object> map);

    Integer checkOperaSbByShow(Map<String, Object> map);

    Integer updateOperaSbShow(Map<String, Object> map);

    Integer checkOperaSbById(Map<String, Object> map);
}
