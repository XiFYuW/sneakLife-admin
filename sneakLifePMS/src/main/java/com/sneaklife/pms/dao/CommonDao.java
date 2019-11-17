package com.sneaklife.pms.dao;

import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/8/24 9:53
 */
public interface CommonDao {

    int delete(Map<String, Object> map);

    int update(Map<String, Object> map);

    int insert(Map<String, Object> map);
}
