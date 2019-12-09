package com.sneaklife.pms.service.system.dictionary.imp;

import com.sneaklife.pms.cache.SneakLifeAuthorityManagementCacheEvict;
import com.sneaklife.pms.dao.system.dictionary.TypeDictionaryJpa;
import com.sneaklife.pms.dao.system.dictionary.TypeDictionaryMapper;
import com.sneaklife.pms.entity.modal.TableOpera;
import com.sneaklife.pms.service.common.OperaService;
import com.sneaklife.pms.service.system.dictionary.TypeDictionaryService;
import com.sneaklife.ut.exception.SneakLifeSuccessfulException;
import com.sneaklife.ut.log.SneakLifeAnLog;
import com.sneaklife.ut.page.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 */
@Service
@CacheConfig(cacheNames = "SneakLifeAuthorityManagement")
public class TypeDictionaryServiceImp extends CommonService implements TypeDictionaryService {

    private static final Logger log = LoggerFactory.getLogger(TypeDictionaryServiceImp.class);

    @Autowired
    private TypeDictionaryMapper typeDictionaryMapper;

    @Autowired
    private TypeDictionaryJpa typeDictionaryJpa;

    @Autowired
    private OperaService operaService;

    @Override
    @Transactional(readOnly = true)
    @Cacheable
    @SneakLifeAnLog
    public Map<String,Object> getTypeDictionary(Map<String, Object> map, PageInfo pageInfo) throws Exception {
        Page<Map<String, Object>> page = typeDictionaryJpa.findAllPage(getPageable(pageInfo));
        return pageToMap(page);
    }

    @Override
    @Transactional
    @Cacheable
    @SneakLifeAnLog
    public TableOpera typeDictionary(Map<String, Object> map) {
        map.put("isShow", 0);
        return operaService.buildOperaBody(map, false);
    }

    @Override
    @Transactional(rollbackFor = Exception.class,noRollbackFor = SneakLifeSuccessfulException.class)
    @SneakLifeAuthorityManagementCacheEvict
    @SneakLifeAnLog
    public void insertTypeDictionary(Map<String, Object> map) throws Exception {
        insert(typeDictionaryMapper, map);
    }

    @Override
    @Transactional(rollbackFor = Exception.class,noRollbackFor = SneakLifeSuccessfulException.class)
    @SneakLifeAuthorityManagementCacheEvict
    @SneakLifeAnLog
    public void updateTypeDictionary(Map<String, Object> map) throws Exception {
        update(typeDictionaryMapper, map);
    }

    @Override
    @Transactional(rollbackFor = Exception.class,noRollbackFor = SneakLifeSuccessfulException.class)
    @SneakLifeAuthorityManagementCacheEvict
    @SneakLifeAnLog
    public void deleteTypeDictionary(Map<String, Object> map) throws Exception {
        delete(typeDictionaryMapper, map);
    }
}
