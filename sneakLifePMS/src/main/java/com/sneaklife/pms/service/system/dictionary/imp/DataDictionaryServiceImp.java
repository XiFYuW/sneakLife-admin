package com.sneaklife.pms.service.system.dictionary.imp;

import com.sneaklife.pms.config.log.SneakLifeAnLog;
import com.sneaklife.pms.dao.system.dictionary.DataDictionaryJpa;
import com.sneaklife.pms.dao.system.dictionary.DataDictionaryMapper;
import com.sneaklife.pms.entity.DataDictionary;
import com.sneaklife.pms.entity.modal.TableOpera;
import com.sneaklife.pms.service.common.CommonService;
import com.sneaklife.pms.service.common.OperaService;
import com.sneaklife.pms.service.system.dictionary.DataDictionaryService;
import com.sneaklife.ut.code.exception.SneakLifeException;
import com.sneaklife.ut.code.page.PageInfo;
import com.sneaklife.ut.common.CommonUtil;
import com.sneaklife.ut.date.DateUtil;
import com.sneaklife.ut.interfaces.ParameterTransformation;
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
    @SneakLifeAnLog
    public void insertDataDictionary(Map<String,Object> map) throws Exception{
        map.put("value", DateUtil.getMilli());
        int t = dataDictionaryMapper.insertDataDictionary(map);
        if(t != 1){
            throw new SneakLifeException(CommonUtil.respResultTJSB());
        }
        throw new SneakLifeException(CommonUtil.respResultTJCG());
    }

    @Override
    @SneakLifeAnLog
    public ResponseEntity<String> getDataDictionary(Map<String, Object> map, PageInfo pageInfo) throws Exception{
        Page<DataDictionary> page = getPageData(map, pageInfo, dataDictionaryJpa);
        return CommonUtil.respResultDataSUCCEED(page);
    }

    @Override
    @SneakLifeAnLog
    public ResponseEntity<String> dataDictionary(Map<String, Object> map) throws Exception{
        map.put("isShow",0);
        TableOpera tableOpera = operaService.buildOperaBody(map,false);
        return CommonUtil.respResultDataSUCCEED(tableOpera);
    }

    @Override
    @SneakLifeAnLog
    public void updateDataDictionary(Map<String, Object> map) throws Exception{
        int t = dataDictionaryMapper.updateDataDictionary(map);
        if(t != 1){
            throw new SneakLifeException(CommonUtil.respResultXGSB());
        }
        throw new SneakLifeException(CommonUtil.respResultXGCG());
    }

    @Override
    @SneakLifeAnLog
    public void deleteDataDictionary(Map<String, Object> map) throws Exception {
        int t = dataDictionaryMapper.deleteDataDictionary(map);
        if(t != 1){
            throw new SneakLifeException(CommonUtil.respResultSCSB());
        }
        throw new SneakLifeException(CommonUtil.respResultSCCG());
    }

    @Override
    @SneakLifeAnLog
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
        return CommonUtil.respResultDataSUCCEED(map);
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
