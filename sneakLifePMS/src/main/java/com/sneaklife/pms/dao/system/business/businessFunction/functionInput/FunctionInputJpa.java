package com.sneaklife.pms.dao.system.business.businessFunction.functionInput;

import com.sneaklife.pms.entity.OperaIn;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface FunctionInputJpa extends JpaRepository<OperaIn, String>, JpaSpecificationExecutor<OperaIn> {

    @Query(value = "select new map" +
            "(oi.id as id,oi.field as field,oi.htmlType as htmlType,oi.textName as textName,dd.name as htmlTypeName) " +
            "from OperaIn oi inner join DataDictionary dd on oi.htmlType = dd.value " +
            "where oi.isDel = 0 and oi.menuId=?1")
    Page<Map<String,Object>> findAllPageByMenuId(String menuId, Pageable pageable);
}
