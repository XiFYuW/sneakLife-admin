package com.sneaklife.pms.dao;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/8/24 9:53
 */
public interface CommonDao {

    /**
     * 分页查询所有
     * @param map 参数
     * @return Page<Map<String,Object>>
     */
    Page<Map<String,Object>> findAllPage(Map<String, Object> map);

    /**
     * 通用逻辑删除
     * @param map 参数
     * @return int
     */
    int delete(@Param("map") Map<String, Object> map);

    /**
     * 通用修改
     * @param map 参数
     * @return int
     */
    int update(Map<String, Object> map);

    /**
     * 通用插入
     * @param map 参数
     * @return int
     */
    int insert(Map<String, Object> map);
}
