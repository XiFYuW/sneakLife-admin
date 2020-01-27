package com.sneaklife.pms.service.system.dictionary.imp;

import com.sneaklife.config.cache.SneakLifeAuthorityManagementCacheEvict;
import com.sneaklife.pms.dao.system.dictionary.TypeDictionaryMapper;
import com.sneaklife.pms.entity.modal.TableOpera;
import com.sneaklife.pms.service.common.CommonService;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 */
@Service("typeDictionaryServiceImp")
@CacheConfig(cacheNames = "SneakLifeAuthorityManagement")
public class TypeDictionaryServiceImp extends CommonService implements TypeDictionaryService {

    private static final Logger log = LoggerFactory.getLogger(TypeDictionaryServiceImp.class);

    private final TypeDictionaryMapper typeDictionaryMapper;

    private final OperaService operaService;

    @Autowired
    public TypeDictionaryServiceImp(TypeDictionaryMapper typeDictionaryMapper, OperaService operaService) {
        this.typeDictionaryMapper = typeDictionaryMapper;
        this.operaService = operaService;
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable
    @SneakLifeAnLog
    public Map<String,Object> getData(Map<String, Object> map, PageInfo pageInfo) throws Exception {
        return super.findAllPage(typeDictionaryMapper, map, pageInfo);
    }

    @Override
    @Transactional
    @Cacheable
    @SneakLifeAnLog
    public TableOpera buildData(Map<String, Object> map) throws Exception{
        return operaService.buildOperaBody(map, false);
    }

    @Override
    @Transactional(rollbackFor = Exception.class,noRollbackFor = SneakLifeSuccessfulException.class)
    @SneakLifeAuthorityManagementCacheEvict
    @SneakLifeAnLog
    public void insert(Map<String, Object> map) throws Exception {
        insert(typeDictionaryMapper, map);
    }

    @Override
    @Transactional(rollbackFor = Exception.class,noRollbackFor = SneakLifeSuccessfulException.class)
    @SneakLifeAuthorityManagementCacheEvict
    @SneakLifeAnLog
    public void update(Map<String, Object> map) throws Exception {
        update(typeDictionaryMapper, map);
    }

    @Override
    @Transactional(rollbackFor = Exception.class,noRollbackFor = SneakLifeSuccessfulException.class)
    @SneakLifeAuthorityManagementCacheEvict
    @SneakLifeAnLog
    public void delete(Map<String, Object> map) throws Exception {
        delete(typeDictionaryMapper, map);
    }
}
