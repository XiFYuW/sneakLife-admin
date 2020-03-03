package com.sneaklife.pms.dao.system.authority.roleConfig;

import com.sneaklife.pms.dao.CommonDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/8/4 10:07
 */
@Mapper
public interface RoleConfigMapper extends CommonDao {

    /**
     * 获取没有逻辑删除的数据
     * @param isDel 是否删除（0：未删除；1：已删除）
     * @return List<Map<String,Object>>
     */
    List<Map<String,Object>> getByIsDel(@Param("isDel") int isDel);
}
