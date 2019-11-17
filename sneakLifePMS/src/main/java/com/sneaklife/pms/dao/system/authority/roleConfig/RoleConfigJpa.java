package com.sneaklife.pms.dao.system.authority.roleConfig;

import com.sneaklife.pms.entity.RoleConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/8/4 10:07
 */
@Repository
public interface RoleConfigJpa extends JpaRepository<RoleConfig, String>, JpaSpecificationExecutor<RoleConfig> {

}
