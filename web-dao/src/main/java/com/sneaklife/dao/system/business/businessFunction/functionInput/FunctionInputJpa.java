package com.sneaklife.dao.system.business.businessFunction.functionInput;

import com.sneaklife.dao.entity.OperaIn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FunctionInputJpa extends JpaRepository<OperaIn, String>, JpaSpecificationExecutor<OperaIn> {

}
