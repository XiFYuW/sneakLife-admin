package com.sneaklife.service.system.dictionary.imp;

import com.sneaklife.common.CommonUtil;
import com.sneaklife.dao.entity.DataDictionary;
import com.sneaklife.dao.entity.modal.TableOpera;
import com.sneaklife.dao.system.dictionary.DataDictionaryJpa;
import com.sneaklife.dao.system.dictionary.DataDictionaryMapper;
import com.sneaklife.exception.SneakLifeException;
import com.sneaklife.page.PageInfo;;
import com.sneaklife.service.CommonJpaService;
import com.sneaklife.service.system.OperaService;
import com.sneaklife.service.system.dictionary.DataDictionaryService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 */
@Service
public class DataDictionaryServiceImp extends CommonJpaService implements DataDictionaryService {

    private static Logger log = LoggerFactory.getLogger(DataDictionaryServiceImp.class);

    @Autowired
    private DataDictionaryMapper dataDictionaryMapper;

    @Autowired
    private DataDictionaryJpa dataDictionaryJpa;

    @Autowired
    private OperaService operaService;

    @Override
    public void insertDataDictionary(Map<String,Object> map) throws Exception{
        int t = dataDictionaryMapper.insertDataDictionary(map);
        if(t != 1){
            throw new SneakLifeException(CommonUtil.respResultTJSB());
        }
        throw new SneakLifeException(CommonUtil.respResultTJCG());
    }

    @Override
    public ResponseEntity<String> getDataDictionary(Map<String, Object> map, PageInfo pageInfo) throws Exception{
        Page<DataDictionary> page = getPageData(map, pageInfo, dataDictionaryJpa);
        return CommonUtil.respResultDataSUCCEED(page);
    }

    @Override
    public ResponseEntity<String> dataDictionary(Map<String, Object> map) throws Exception{
        map.put("isShow",0);
        TableOpera tableOpera = operaService.buildOperaBody(map,false);
        return CommonUtil.respResultDataSUCCEED(tableOpera);
    }

    @Override
    public void updateDataDictionary(Map<String, Object> map) throws Exception{
        int t = dataDictionaryMapper.updateDataDictionary(map);
        if(t != 1){
            throw new SneakLifeException(CommonUtil.respResultXGSB());
        }
        throw new SneakLifeException(CommonUtil.respResultXGCG());
    }

    @Override
    public void deleteDataDictionary(Map<String, Object> map) throws Exception {
        int t = dataDictionaryMapper.deleteDataDictionary(map);
        if(t != 1){
            throw new SneakLifeException(CommonUtil.respResultSCSB());
        }
        throw new SneakLifeException(CommonUtil.respResultSCCG());
    }
}
