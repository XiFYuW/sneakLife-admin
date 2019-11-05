package com.sneaklife.dao.system;

import com.sneaklife.dao.entity.SystemMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemMenuJpa extends JpaRepository<SystemMenu, String>, JpaSpecificationExecutor<SystemMenu> {

}
