package com.sneaklife.dao.system.authority.opera;

import com.sneaklife.dao.entity.OperaSb;;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface OperaSbJpa extends JpaRepository<OperaSb, String>, JpaSpecificationExecutor<OperaSb> {

}
