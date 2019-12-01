package com.sneaklife.pms.dao.system;

import com.sneaklife.pms.entity.SystemMenu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface SystemMenuJpa extends JpaRepository<SystemMenu, String>, JpaSpecificationExecutor<SystemMenu> {

    @Query(value = "select new map(sm.id as id,sm.name as name,sm.dataUrl as dataUrl,sm.itemUrl as itemUrl," +
            "sm.pageUrl as pageUrl,sm.tab as tab,sm.pid as pid,coalesce(sm1.tab,'') as pidName) " +
            "from SystemMenu sm left join SystemMenu sm1 on sm.pid = sm1.id " +
            "where sm.isDel = 0")
    Page<Map<String,Object>> findAllPage(Pageable pageable);
}
