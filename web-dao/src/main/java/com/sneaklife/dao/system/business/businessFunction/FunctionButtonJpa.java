package com.sneaklife.dao.system.business.businessFunction;

import com.sneaklife.dao.entity.OperaSb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FunctionButtonJpa extends JpaRepository<OperaSb, String>, JpaSpecificationExecutor<OperaSb> {

}
