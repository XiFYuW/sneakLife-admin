package com.sneaklife.pms.dao.system.authority.opera;

import com.sneaklife.pms.entity.OperaBo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface OperaBoJpa extends JpaRepository<OperaBo, String>, JpaSpecificationExecutor<OperaBo> {

    @Query(value = "select new map" +
            "(ob.id as id,ob.field as field,ob.htmlType as htmlType,ob.textName as textName," +
            "dd.name as htmlTypeName) " +
            "from OperaBo ob inner join DataDictionary dd on ob.htmlType = dd.value " +
            "where ob.isDel = 0 and ob.menuId=?1")
    Page<Map<String,Object>> findAllPageByMenuId(String menuId, Pageable pageable);
}
