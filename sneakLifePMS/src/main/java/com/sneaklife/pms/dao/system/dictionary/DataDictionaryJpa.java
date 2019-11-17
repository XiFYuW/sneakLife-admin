package com.sneaklife.pms.dao.system.dictionary;

import com.sneaklife.pms.entity.DataDictionary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DataDictionaryJpa extends JpaRepository<DataDictionary, String>, JpaSpecificationExecutor<DataDictionary> {

}
