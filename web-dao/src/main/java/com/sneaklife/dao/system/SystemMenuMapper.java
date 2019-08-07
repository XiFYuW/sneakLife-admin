package com.sneaklife.dao.system;

import com.sneaklife.dao.entity.SystemMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/8/5 14:46
 */
@Mapper
public interface SystemMenuMapper {

    List<SystemMenu> getByIsDel(@Param("isDel") int isDel);

    List<SystemMenu> getByBatchId(@Param("array") String[] id);
}
