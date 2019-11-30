package com.sneaklife.pms.dao.system.authority.userRole;

import com.sneaklife.pms.entity.UserRole;
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
public interface UserRoleJpa extends JpaRepository<UserRole, String>, JpaSpecificationExecutor<UserRole> {

    @Query(value = "select new map(ur.userId as userId,ur.id as id,ur.roleId as roleId," +
            "u.name as userName,rc.name as roleName,rc.id as value,rc.name as text) " +
            "from UserRole ur inner join User u on ur.userId = u.uuid " +
            "inner join RoleConfig rc on ur.roleId = rc.id where ur.isDel = 0")
    Page<Map<String,Object>> findAllPage(Pageable pageable);
}
