package com.sneaklife.pms.dao.system.authority.opera;

import com.sneaklife.pms.entity.OperaSb;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

;import java.util.Map;

@Repository
public interface OperaSbJpa extends JpaRepository<OperaSb, String>, JpaSpecificationExecutor<OperaSb> {

    @Query(value = "select new map" +
            "(os.id as id,os.icon as icon,os.code as code,os.url as url,os.type as type," +
            "dd2.name as typeName,dd1.name as iconName,dd.name as codeName) " +
            "from OperaSb os inner join DataDictionary dd on os.code = dd.value " +
            "inner join DataDictionary dd1 on os.icon = dd1.value " +
            "inner join DataDictionary dd2 on os.type = dd2.value " +
            "where os.isDel = 0 and os.isShow = 0 and os.menuId=?1")
    Page<Map<String,Object>> findAllPageByMenuId(String menuId, Pageable pageable);
}
