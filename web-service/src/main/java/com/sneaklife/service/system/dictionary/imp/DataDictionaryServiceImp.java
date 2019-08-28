package com.sneaklife.service.system.dictionary.imp;

import com.sneaklife.common.CommonUtil;
import com.sneaklife.dao.entity.DataDictionary;
import com.sneaklife.dao.entity.modal.TableOpera;
import com.sneaklife.dao.system.dictionary.DataDictionaryJpa;
import com.sneaklife.dao.system.dictionary.DataDictionaryMapper;
import com.sneaklife.date.DateUtil;
import com.sneaklife.exception.SneakLifeException;
import com.sneaklife.interfaces.ParameterTransformation;
import com.sneaklife.log.SneakLifeLog;
import com.sneaklife.page.PageInfo;
import com.sneaklife.service.CommonService;
import com.sneaklife.service.system.OperaService;
import com.sneaklife.service.system.dictionary.DataDictionaryService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 */
@Service
public class DataDictionaryServiceImp extends CommonService implements DataDictionaryService,
        ParameterTransformation<DataDictionary, Map<String,Object>, List<Map<String, Object>>> {

    private static Logger log = LoggerFactory.getLogger(DataDictionaryServiceImp.class);

    @Autowired
    private DataDictionaryMapper dataDictionaryMapper;

    @Autowired
    private DataDictionaryJpa dataDictionaryJpa;

    @Autowired
    private OperaService operaService;

    @Override
    @SneakLifeLog
    public void insertDataDictionary(Map<String,Object> map) throws Exception{
        map.put("value", DateUtil.getSecond() >> 2);
        int t = dataDictionaryMapper.insertDataDictionary(map);
        if(t != 1){
            throw new SneakLifeException(CommonUtil.respResultTJSB());
        }
        throw new SneakLifeException(CommonUtil.respResultTJCG());
    }

    @Override
    @SneakLifeLog
    public ResponseEntity<String> getDataDictionary(Map<String, Object> map, PageInfo pageInfo) throws Exception{
        Page<DataDictionary> page = getPageData(map, pageInfo, dataDictionaryJpa);
        return CommonUtil.respResultDataSUCCEED(page);
    }

    @Override
    @SneakLifeLog
    public ResponseEntity<String> dataDictionary(Map<String, Object> map) throws Exception{
        map.put("isShow",0);
        TableOpera tableOpera = operaService.buildOperaBody(map,false);
        return CommonUtil.respResultDataSUCCEED(tableOpera);
    }

    @Override
    @SneakLifeLog
    public void updateDataDictionary(Map<String, Object> map) throws Exception{
        int t = dataDictionaryMapper.updateDataDictionary(map);
        if(t != 1){
            throw new SneakLifeException(CommonUtil.respResultXGSB());
        }
        throw new SneakLifeException(CommonUtil.respResultXGCG());
    }

    @Override
    @SneakLifeLog
    public void deleteDataDictionary(Map<String, Object> map) throws Exception {
        int t = dataDictionaryMapper.deleteDataDictionary(map);
        if(t != 1){
            throw new SneakLifeException(CommonUtil.respResultSCSB());
        }
        throw new SneakLifeException(CommonUtil.respResultSCCG());
    }

    @Override
    @SneakLifeLog
    public ResponseEntity<String> getByType(Map<String, Object> map) {
        String type = String.valueOf(map.get("type"));
        List<DataDictionary> list = dataDictionaryMapper.getByType(type);
        List<Map<String,Object>> data = new ArrayList<>();
        list.forEach(dataDictionary -> data.add(paramTrans(new HashMap<>(), dataDictionary)));
        map.clear();
        map.put("title","select data");
        map.put("data", data);
        return CommonUtil.respResultDataSUCCEED(data);
    }

    @Override
    @SneakLifeLog
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
