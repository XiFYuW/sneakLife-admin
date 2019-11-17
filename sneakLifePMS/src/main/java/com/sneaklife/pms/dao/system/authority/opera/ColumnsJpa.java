package com.sneaklife.pms.dao.system.authority.opera;

import com.sneaklife.pms.entity.Columns;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ColumnsJpa extends JpaRepository<Columns, String>, JpaSpecificationExecutor<Columns> {

}
