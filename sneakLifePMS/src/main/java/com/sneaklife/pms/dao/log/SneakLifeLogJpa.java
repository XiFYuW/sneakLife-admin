package com.sneaklife.pms.dao.log;

import com.sneaklife.pms.entity.SneakLifeLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SneakLifeLogJpa extends JpaRepository<SneakLifeLog, String>, JpaSpecificationExecutor<SneakLifeLog> {

}
