package com.sneaklife.pms.dao.system.authority.opera;

import com.sneaklife.pms.entity.Columns;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;

public interface ColumnsJpa extends JpaRepository<Columns, String>, JpaSpecificationExecutor<Columns> {

    @Query(value = "select new map" +
            "(cl.id as id,cl.title as title,cl.field as field,cl.align as align,cl.isShow as isShow,dd.name as alignName) " +
            "from Columns cl inner join DataDictionary dd on cl.align = dd.value " +
            "where cl.isDel = 0 and cl.menuId=?1")
    Page<Map<String,Object>> findAllPageByMenuId(String menuId, Pageable pageable);
}
