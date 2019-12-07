package com.sneaklife.pms.dao.system.authority.opera;

import com.sneaklife.pms.entity.OperaIn;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface OperaInJpa extends JpaRepository<OperaIn, String>, JpaSpecificationExecutor<OperaIn> {

    @Query(value = "select new map" +
            "(oi.id as id,oi.field as field,oi.htmlType as htmlType,oi.textName as textName," +
            "dd.name as htmlTypeName,oi.rule as rule,dd1.name as ruleName) " +
            "from OperaIn oi inner join DataDictionary dd on oi.htmlType = dd.value " +
            "inner join DataDictionary dd1 on oi.rule = dd1.value " +
            "where oi.isDel = 0 and oi.menuId=?1")
    Page<Map<String,Object>> findAllPageByMenuId(String menuId, Pageable pageable);
}
