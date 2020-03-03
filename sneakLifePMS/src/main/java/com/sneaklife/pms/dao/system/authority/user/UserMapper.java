package com.sneaklife.pms.dao.system.authority.user;

import com.sneaklife.pms.dao.CommonDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/8/4 10:07
 */
@Mapper
public interface UserMapper extends CommonDao {

    /**
     * 检查是否存在
     * @param map 条件参数
     * @return Map<String, Object>
     */
    Map<String, Object> checkExist(Map<String, Object> map);
}
