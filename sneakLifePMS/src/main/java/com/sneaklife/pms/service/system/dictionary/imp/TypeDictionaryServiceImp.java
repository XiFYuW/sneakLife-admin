package com.sneaklife.pms.service.system.dictionary.imp;

import com.sneaklife.pms.dao.system.dictionary.TypeDictionaryJpa;
import com.sneaklife.pms.dao.system.dictionary.TypeDictionaryMapper;
import com.sneaklife.pms.entity.modal.TableOpera;
import com.sneaklife.pms.service.common.CommonService;
import com.sneaklife.pms.service.common.OperaService;
import com.sneaklife.pms.service.system.dictionary.TypeDictionaryService;
import com.sneaklife.ut.iws.IwsContext;
import com.sneaklife.ut.page.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 */
@Service
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
    public ResponseEntity<String> getTypeDictionary(Map<String, Object> map, PageInfo pageInfo) throws Exception {
        Page<Map<String, Object>> page = typeDictionaryJpa.findAllPage(getPageable(pageInfo));
        return IwsContext.respResultBodyToSC(pageToMap(page));
    }

    @Override
    @Transactional
    public ResponseEntity<String> typeDictionary(Map<String, Object> map) throws Exception {
        map.put("isShow", 0);
        TableOpera tableOpera = operaService.buildOperaBody(map, false);
        return IwsContext.respResultBodyToSC(tableOpera);
    }

    @Override
    @Transactional
    public void insertTypeDictionary(Map<String, Object> map) throws Exception {
        insert(typeDictionaryMapper, map);
    }

    @Override
    @Transactional
    public void updateTypeDictionary(Map<String, Object> map) throws Exception {
        update(typeDictionaryMapper, map);
    }

    @Override
    @Transactional
    public void deleteTypeDictionary(Map<String, Object> map) throws Exception {
        delete(typeDictionaryMapper, map);
    }
}
