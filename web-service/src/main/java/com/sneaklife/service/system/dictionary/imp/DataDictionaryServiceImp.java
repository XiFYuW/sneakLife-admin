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

import java.util.Iterator;
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
        return null;
    }

    @Override
    public ResponseEntity<String> getDataDictionary(Map<String, Object> map) {
        Pageable pageable = PageRequest.of(0, 10, Sort.Direction.ASC, "id");
        Page<DataDictionary> page =  dataDictionaryJpa.findAll(pageable);
        List<DataDictionary> rows = page.getContent();
        PageInfo pageInfo = new PageInfo();
        pageInfo.setRows(rows);
        pageInfo.setTotal(page.getTotalElements());
        return CommonUtil.respResultDataSUCCEED(pageInfo);
    }

    @Override
    public ResponseEntity<String> dataDictionary(Map<String, Object> map) {
        String dataUrl = "getDataDictionary";
        List<Columns> columnsList = columnsMapper.findColumnsByShow(map);
        Table table = new Table(dataUrl,columnsList);
        List<OperaSb> operaSbList = operaSbJpa.findAll();
        List<OperaIn> operaInList = operaInMapper.findOperaByShow();
        Opera opera = new Opera(operaSbList,operaInList);
        TableOpera tableOpera = new TableOpera(opera,table);
        return CommonUtil.respResultDataSUCCEED(tableOpera);
    }
}
