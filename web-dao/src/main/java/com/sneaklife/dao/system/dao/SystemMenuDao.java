package com.sneaklife.dao.system.dao;

import com.sneaklife.dao.system.entity.SystemMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemMenuDao extends JpaRepository<SystemMenu, String> {

}
