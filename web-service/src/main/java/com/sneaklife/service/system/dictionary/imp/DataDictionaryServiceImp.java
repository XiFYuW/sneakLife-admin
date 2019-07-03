package com.sneaklife.service.system.dictionary.imp;

import com.sneaklife.common.CommonUtil;
import com.sneaklife.dao.entity.Columns;
import com.sneaklife.dao.entity.DataDictionary;
import com.sneaklife.dao.entity.OperaIn;
import com.sneaklife.dao.entity.OperaSb;
import com.sneaklife.dao.entity.modal.Opera;
import com.sneaklife.dao.entity.modal.Table;
import com.sneaklife.dao.entity.modal.TableOpera;
import com.sneaklife.dao.system.columns.ColumnsMapper;
import com.sneaklife.dao.system.dictionary.DataDictionaryJpa;
import com.sneaklife.dao.system.dictionary.DataDictionaryMapper;
import com.sneaklife.dao.system.opera.OperaInMapper;
import com.sneaklife.dao.system.opera.OperaSbJpa;
import com.sneaklife.dao.system.opera.OperaSbMapper;
import com.sneaklife.exception.SneakLifeException;
import com.sneaklife.page.PageInfo;
import com.sneaklife.resp.RespCode;
import com.sneaklife.service.system.OperaService;
import com.sneaklife.service.system.dictionary.DataDictionaryService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class DataDictionaryServiceImp implements DataDictionaryService {

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
        if(!CommonUtil.isNull(pageInfo)){
            return CommonUtil.respResult(RespCode.MSG_PAGE_ERR.toValue(), RespCode.MSG_PAGE_ERR.toMsg());
        }
        Pageable pageable = PageRequest.of(pageInfo.getPage(), pageInfo.getRows(), Sort.Direction.ASC, "id");
        Page<DataDictionary> page = dataDictionaryJpa.findAll((Specification<DataDictionary>) (root, criteriaQuery, criteriaBuilder) -> {
            Path<String> isDel = root.get("isDel");
            return criteriaBuilder.equal(isDel.as(Integer.class),0);
        }, pageable);
        return CommonUtil.respResultDataSUCCEED(page);
    }

    @Override
    public ResponseEntity<String> dataDictionary(Map<String, Object> map) throws Exception{
        TableOpera tableOpera = operaService.buildOperaBody(map);
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
