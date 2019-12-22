package com.sneaklife.pms.service.system.dictionary.imp;

import com.sneaklife.pms.cache.SneakLifeAuthorityManagementCacheEvict;
import com.sneaklife.pms.dao.system.dictionary.DataDictionaryMapper;
import com.sneaklife.pms.dao.system.dictionary.TypeDictionaryMapper;
import com.sneaklife.pms.entity.modal.TableOpera;
import com.sneaklife.pms.service.common.CommonService;
import com.sneaklife.pms.service.common.OperaService;
import com.sneaklife.pms.service.system.dictionary.DataDictionaryService;
import com.sneaklife.ut.exception.SneakLifeSuccessfulException;
import com.sneaklife.ut.log.SneakLifeAnLog;
import com.sneaklife.ut.page.PageInfo;
import com.sneaklife.ut.date.DateUtil;
import com.sneaklife.ut.string.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author https://github.com/XiFYuW
 */
@Service
@CacheConfig(cacheNames = "SneakLifeAuthorityManagement")
public class DataDictionaryServiceImp extends CommonService implements DataDictionaryService {

    private static final Logger log = LoggerFactory.getLogger(DataDictionaryServiceImp.class);

    private final DataDictionaryMapper dataDictionaryMapper;

    private final TypeDictionaryMapper typeDictionaryMapper;

    private final OperaService operaService;

    @Autowired
    public DataDictionaryServiceImp(DataDictionaryMapper dataDictionaryMapper, TypeDictionaryMapper typeDictionaryMapper, OperaService operaService) {
        this.dataDictionaryMapper = dataDictionaryMapper;
        this.typeDictionaryMapper = typeDictionaryMapper;
        this.operaService = operaService;
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable
    @SneakLifeAnLog
    public Map<String,Object> getDataDictionary(Map<String, Object> map, PageInfo pageInfo) throws Exception {
        return super.findAllPage(dataDictionaryMapper, map, pageInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @Cacheable
    @SneakLifeAnLog
    public TableOpera dataDictionary(Map<String, Object> map) {
        map.put("isShow", 0);
        return operaService.buildOperaBody(map, false);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, noRollbackFor = SneakLifeSuccessfulException.class)
    @SneakLifeAuthorityManagementCacheEvict
    @SneakLifeAnLog
    public void insertDataDictionary(Map<String, Object> map) throws Exception {
        String value = String.valueOf(map.get("value"));
        if (StringUtil.isEmpty(value)) {
            map.put("value", DateUtil.getMilli());
        }
        insert(dataDictionaryMapper, map);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, noRollbackFor = SneakLifeSuccessfulException.class)
    @SneakLifeAuthorityManagementCacheEvict
    @SneakLifeAnLog
    public void updateDataDictionary(Map<String, Object> map) throws Exception {
        update(dataDictionaryMapper, map);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, noRollbackFor = SneakLifeSuccessfulException.class)
    @SneakLifeAuthorityManagementCacheEvict
    @SneakLifeAnLog
    public void deleteDataDictionary(Map<String, Object> map) throws Exception {
        delete(dataDictionaryMapper, map);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable
    @SneakLifeAnLog
    public Map<String, Object> getByType(Map<String, Object> map) {
        String express = String.valueOf(map.get("express"));
        String menuId = String.valueOf(map.get("menuId"));
        Map<String, Object> item = buildSelectKey(express, menuId);
        map.clear();
        map.put("title", "select data");
        map.put("data", item);
        return map;
    }

    private Map<String, Object> buildSelectKey(String express, String menuId) {
        Map<String, Object> data = new HashMap<>();
        List<Map<String, Object>> selectKey = operaService.getSelectsKyByMenuId(menuId, "selects");
        String[] types = express.split(",");
        for (String type : types) {
            String[] match = type.split(":");
            for (Map<String, Object> select : selectKey) {
                if (match[0].equals(String.valueOf(select.get("id")))) {
                    String field = String.valueOf(select.get("field"));
                    String va = match[1];
                    if ("*".equals(va)) {
                        List<Map<String, Object>> item = typeDictionaryMapper.getIdName();
                        data.put(field, item);
                    } else {
                        List<Map<String, Object>> item = dataDictionaryMapper.getNameValueByTypeId(Long.parseLong(va));
                        data.put(field, item);
                    }
                }
            }
        }
        return data;
    }

}
