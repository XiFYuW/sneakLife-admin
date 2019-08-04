package com.sneaklife.dao.system.roleFunction;

import com.sneaklife.dao.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/8/4 10:07
 */
@Repository
public interface RoleFunctionJpa extends JpaRepository<Role, String>, JpaSpecificationExecutor<Role> {

}
