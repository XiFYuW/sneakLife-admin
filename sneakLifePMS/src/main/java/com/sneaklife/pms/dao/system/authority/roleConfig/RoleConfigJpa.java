package com.sneaklife.pms.dao.system.authority.roleConfig;

import com.sneaklife.pms.entity.RoleConfig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/8/4 10:07
 */
@Repository
public interface RoleConfigJpa extends JpaRepository<RoleConfig, String>, JpaSpecificationExecutor<RoleConfig> {

    @Query(value = "select new map(rc.name as name,rc.id as id,rc.type as type,dd.name as typeName) " +
            "from RoleConfig rc inner join DataDictionary dd on rc.type = dd.value where rc.isDel = 0")
    Page<Map<String,Object>> findAllPage(Pageable pageable);
}
