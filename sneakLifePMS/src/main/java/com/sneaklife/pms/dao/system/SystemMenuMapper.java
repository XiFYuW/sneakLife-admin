package com.sneaklife.pms.dao.system;

import com.sneaklife.pms.dao.CommonDao;
import com.sneaklife.pms.entity.SystemMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/8/5 14:46
 */
@Mapper
public interface SystemMenuMapper extends CommonDao {

    List<SystemMenu> getByIsDel(@Param("isDel") int isDel);

    List<SystemMenu> getByBatchId(@Param("array") String[] id);

    List<SystemMenu> getByNotBatchId(@Param("array") String[] id);

    String getItemUrlById(@Param("id") String id);
}
