package com.sneaklife.pms.dao.system.authority.opera;

import com.sneaklife.pms.dao.CommonDao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
/**
 * @author https://github.com/XiFYuW
 */
@Mapper
public interface ColumnsMapper extends CommonDao {

    /**
     * 查找功能字段
     * @param map 条件参数
     * @return List<Map<String,Object>>
     */
    List<Map<String,Object>> findColumns(Map<String, Object> map);

}
