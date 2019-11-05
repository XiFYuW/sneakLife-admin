package com.sneaklife.dao.system.authority.roleConfig;

import com.sneaklife.dao.entity.RoleConfig;
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
