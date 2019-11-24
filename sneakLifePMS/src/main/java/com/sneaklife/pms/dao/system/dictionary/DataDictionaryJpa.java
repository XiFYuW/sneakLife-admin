package com.sneaklife.pms.dao.system.dictionary;

import com.sneaklife.pms.entity.DataDictionary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface DataDictionaryJpa extends JpaRepository<DataDictionary, String>, JpaSpecificationExecutor<DataDictionary> {


    @Query(value = "select new map(dd.name as name,dd.typeId as typeId,dd.value as value,td.name as typeName) from DataDictionary dd,TypeDictionary td where td.id = dd.typeId")
    Page<Map<String,Object>> findAllPage(Pageable pageable);

}
