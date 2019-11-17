package com.sneaklife.pms.dao.system.business.businessFunction.fcuntionColumns;

import com.sneaklife.pms.entity.Columns;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FunctionColumnsJpa extends JpaRepository<Columns, String>, JpaSpecificationExecutor<Columns> {

}
