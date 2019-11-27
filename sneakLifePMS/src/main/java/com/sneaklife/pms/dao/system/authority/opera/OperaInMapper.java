package com.sneaklife.pms.dao.system.authority.opera;

import com.sneaklife.pms.dao.CommonDao;
import com.sneaklife.pms.entity.OperaIn;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 */
@Mapper
public interface OperaInMapper extends CommonDao {

    List<OperaIn> findOperaInByShow(Map<String, Object> map);

    Integer updateOperaInShow(Map<String, Object> map);

    Integer checkOperaInByShow(Map<String, Object> map);

    Integer checkOperaInById(Map<String, Object> map);

    List<Map<String,Object>> getSelectsKyByMenuId(@Param("menuId") String menuId);
}
