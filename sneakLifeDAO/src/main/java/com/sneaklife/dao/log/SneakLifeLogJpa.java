package com.sneaklife.dao.log;

import com.sneaklife.dao.entity.SneakLifeLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SneakLifeLogJpa extends JpaRepository<SneakLifeLog, String>, JpaSpecificationExecutor<SneakLifeLog> {

}
