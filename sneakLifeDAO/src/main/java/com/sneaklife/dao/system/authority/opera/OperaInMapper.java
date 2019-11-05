package com.sneaklife.dao.system.authority.opera;

import com.sneaklife.dao.entity.OperaIn;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 */
@Mapper
public interface OperaInMapper {

    List<OperaIn> findOperaInByShow(Map<String,Object> map);

    Integer updateOperaInShow(Map<String,Object> map);

    Integer checkOperaInByShow(Map<String,Object> map);

    Integer checkOperaInById(Map<String,Object> map);
}
