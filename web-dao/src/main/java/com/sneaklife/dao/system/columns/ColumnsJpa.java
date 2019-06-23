package com.sneaklife.dao.system.columns;

import com.sneaklife.dao.entity.Columns;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ColumnsJpa extends JpaRepository<Columns, String>, JpaSpecificationExecutor<Columns> {

}
