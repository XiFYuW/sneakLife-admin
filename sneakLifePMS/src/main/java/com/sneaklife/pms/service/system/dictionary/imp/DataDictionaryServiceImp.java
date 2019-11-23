package com.sneaklife.pms.service.system.dictionary.imp;

import com.sneaklife.pms.config.log.SneakLifeAnLog;
import com.sneaklife.pms.dao.system.dictionary.DataDictionaryJpa;
import com.sneaklife.pms.dao.system.dictionary.DataDictionaryMapper;
import com.sneaklife.pms.entity.DataDictionary;
import com.sneaklife.pms.entity.modal.TableOpera;
import com.sneaklife.pms.service.common.CommonService;
import com.sneaklife.pms.service.common.OperaService;
import com.sneaklife.pms.service.system.dictionary.DataDictionaryService;
import com.sneaklife.ut.iws.IwsContext;
import com.sneaklife.ut.page.PageInfo;
import com.sneaklife.ut.date.DateUtil;
import com.sneaklife.ut.interfaces.ParameterTransformation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author https://github.com/XiFYuW
 */
@Service
public class DataDictionaryServiceImp extends CommonService implements DataDictionaryService,
        ParameterTransformation<DataDictionary, Map<String,Object>, List<Map<String, Object>>> {

    private static final Logger log = LoggerFactory.getLogger(DataDictionaryServiceImp.class);

    @Autowired
    private DataDictionaryMapper dataDictionaryMapper;

    @Autowired
    private DataDictionaryJpa dataDictionaryJpa;

    @Autowired
    private OperaService operaService;

    @Override
    @SneakLifeAnLog
    @Transactional(rollbackFor={Exception.class})
    public void insertDataDictionary(Map<String,Object> map) throws Exception{
        map.put("value", DateUtil.getMilli());
        map.put("typeId", Long.valueOf(String.valueOf(map.get("typeDictionary.name"))));
        insert(dataDictionaryMapper, map);
    }

    @Override
    @SneakLifeAnLog
    @Transactional(readOnly = true)
    public ResponseEntity<String> getDataDictionary(Map<String, Object> map, PageInfo pageInfo) throws Exception{
        Page<DataDictionary> page = getPageData(map, pageInfo, dataDictionaryJpa);
        return IwsContext.respResultBodyToSC(page);
    }

    @Override
    @SneakLifeAnLog
    @Transactional(rollbackFor={Exception.class})
    public ResponseEntity<String> dataDictionary(Map<String, Object> map) throws Exception{
        map.put("isShow",0);
        TableOpera tableOpera = operaService.buildOperaBody(map,false);
        return IwsContext.respResultBodyToSC(tableOpera);
    }

    @Override
    @SneakLifeAnLog
    @Transactional(rollbackFor={Exception.class})
    public void updateDataDictionary(Map<String, Object> map) throws Exception{
        update(dataDictionaryMapper, map);
    }

    @Override
    @SneakLifeAnLog
    @Transactional(rollbackFor={Exception.class})
    public void deleteDataDictionary(Map<String, Object> map) throws Exception {
        delete(dataDictionaryMapper, map);
    }

    @Override
    @SneakLifeAnLog
    @Transactional(readOnly = true)
    public ResponseEntity<String> getByType(Map<String, Object> map) {
        String type = String.valueOf(map.get("type"));
        String[] types = type.split(",");
        List<DataDictionary> list = dataDictionaryMapper.getByType(types);
        Map<String,Object> item = new HashMap<>();
        list.forEach(dataDictionary -> {
            String tempKey = dataDictionary.getTempKey();
            List<Map<String,Object>> data;
            if(item.containsKey(tempKey)){
                data = (List<Map<String,Object>>) item.get(tempKey);
                data.add(paramTrans(new HashMap<>(), dataDictionary));
                item.put(tempKey, data);
            }else {
                data = new ArrayList<>();
                data.add(paramTrans(new HashMap<>(), dataDictionary));
                item.put(tempKey, data);
            }
        });
        map.clear();
        map.put("title","select data");
        map.put("data", item);
        return IwsContext.respResultBodyToSC(map);
    }

    @Override
    @SneakLifeAnLog
    public Map<String, Object> paramTrans(Map<String, Object> map, DataDictionary dataDictionary) {
        map.put("name", dataDictionary.getName());
        map.put("value", dataDictionary.getValue());
        return map;
    }

    @Override
    public List<Map<String, Object>> fixedParamTrans(List<Map<String, Object>> maps, Map<String, Object> map) {
        return new ArrayList<>();
    }
}
