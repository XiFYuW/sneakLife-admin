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
import com.sneaklife.page.PageInfo;
import com.sneaklife.resp.RespCode;
import com.sneaklife.service.system.dictionary.DataDictionaryService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
    private ColumnsMapper columnsMapper;

    @Autowired
    private OperaInMapper operaInMapper;

    @Autowired
    private OperaSbJpa operaSbJpa;

    @Override
    public ResponseEntity<String> insertDataDictionary(Map<String,Object> map) {
        int t = dataDictionaryMapper.insertDataDictionary(map);
        if(t != 1){
            return CommonUtil.respResultTJSB();
        }
        return CommonUtil.respResultTJCG();
    }

    @Override
    public ResponseEntity<String> getDataDictionary(Map<String, Object> map, PageInfo pageInfo) {
        if(!CommonUtil.isNull(pageInfo)){
            return CommonUtil.respResult(RespCode.MSG_PAGE_ERR.toValue(), RespCode.MSG_PAGE_ERR.toMsg());
        }
        Pageable pageable = PageRequest.of(pageInfo.getPage(), pageInfo.getRows(), Sort.Direction.ASC, "id");
        Page<DataDictionary> page = dataDictionaryJpa.findAll(pageable);
        return CommonUtil.respResultDataSUCCEED(page);
    }

    @Override
    public ResponseEntity<String> dataDictionary(Map<String, Object> map) {
        List<Columns> columnsList = columnsMapper.findColumnsByShow(map);
        Table table = new Table("",columnsList);
        List<OperaSb> operaSbList = operaSbJpa.findAll();
        List<OperaIn> operaInList = operaInMapper.findOperaByShow();
        Opera opera = new Opera(operaSbList,dispOperaIn(operaInList));
        TableOpera tableOpera = new TableOpera(opera,table);
        return CommonUtil.respResultDataSUCCEED(tableOpera);
    }

    private List<List<OperaIn>> dispOperaIn(List<OperaIn> list){
        List<List<OperaIn>> data = new LinkedList<>();
        List<OperaIn> temp = new ArrayList<>();
        for (int i = 1; i <= list.size(); i++) {
            OperaIn operaIn = list.get(i-1);
            if(i%2 != 0){
                temp.add(operaIn);
            }else {
                temp.add(operaIn);
                data.add(temp);
                temp = new ArrayList<>();
            }
        }
        return data;
    }
}
