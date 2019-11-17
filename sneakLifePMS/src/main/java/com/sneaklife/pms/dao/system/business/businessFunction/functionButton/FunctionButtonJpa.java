package com.sneaklife.pms.dao.system.business.businessFunction.functionButton;

import com.sneaklife.pms.entity.OperaSb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FunctionButtonJpa extends JpaRepository<OperaSb, String>, JpaSpecificationExecutor<OperaSb> {

}
