package com.sneaklife.pms.dao.system.authority.opera;

import com.sneaklife.pms.dao.CommonDao;
import com.sneaklife.pms.entity.OperaIn;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 */
@Mapper
public interface OperaBoMapper extends CommonDao {

    List<OperaIn> findOperaBoByShow(Map<String, Object> map);
}