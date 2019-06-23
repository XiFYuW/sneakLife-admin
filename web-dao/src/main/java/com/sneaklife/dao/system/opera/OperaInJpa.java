package com.sneaklife.dao.system.opera;

import com.sneaklife.dao.entity.OperaIn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface OperaInJpa extends JpaRepository<OperaIn, String>, JpaSpecificationExecutor<OperaIn> {

}
