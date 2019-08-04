package com.sneaklife.dao.system.columns;

import com.sneaklife.dao.entity.Columns;
import org.apache.ibatis.annotations.Mapper;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 */
@Mapper
public interface ColumnsMapper {

    List<Columns> findColumnsByShow(Map<String, Object> map);

    Integer updateColumnsShow(Map<String,Object> map);

    Integer checkColumnsByShow(Map<String,Object> map);

    Integer checkColumnsById(Map<String,Object> map);
}
