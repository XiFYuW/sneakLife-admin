package com.sneaklife.pms.dao.system;

import com.sneaklife.pms.dao.CommonDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/8/5 14:46
 */
@Mapper
public interface SystemMenuMapper extends CommonDao {

    /**
     * 获取没有逻辑删除的数据
     * @param isDel 是否删除（0：未删除；1：已删除）
     * @return List<Map<String,Object>>
     */
    List<Map<String,Object>> getByIsDel(@Param("isDel") int isDel);

    /**
     * 获取第二次页面构造地址
     * @param id 菜单id
     * @return String
     */
    String getItemUrlById(@Param("id") String id);
}
