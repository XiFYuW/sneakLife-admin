package com.sneaklife.pms.dao.system.dictionary;

import com.sneaklife.pms.entity.TypeDictionary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface TypeDictionaryJpa extends JpaRepository<TypeDictionary, String>, JpaSpecificationExecutor<TypeDictionary> {


    @Query(value = "select new map(td.id as id,td.name as name) from TypeDictionary td where td.isDel = 0")
    Page<Map<String,Object>> findAllPage(Pageable pageable);

}
