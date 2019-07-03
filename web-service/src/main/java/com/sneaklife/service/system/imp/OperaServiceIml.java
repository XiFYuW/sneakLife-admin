package com.sneaklife.service.system.imp;

import com.sneaklife.dao.entity.Columns;
import com.sneaklife.dao.entity.OperaIn;
import com.sneaklife.dao.entity.OperaSb;
import com.sneaklife.dao.entity.modal.Opera;
import com.sneaklife.dao.entity.modal.Table;
import com.sneaklife.dao.entity.modal.TableOpera;
import com.sneaklife.dao.system.columns.ColumnsMapper;
import com.sneaklife.dao.system.opera.OperaInMapper;
import com.sneaklife.dao.system.opera.OperaSbMapper;
import com.sneaklife.service.system.OperaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class OperaServiceIml implements OperaService {

    @Autowired
    private ColumnsMapper columnsMapper;

    @Autowired
    private OperaInMapper operaInMapper;

    @Autowired
    private OperaSbMapper operaSbMapper;

    @Override
    public TableOpera buildOperaBody(Map<String, Object> map) {
        List<Columns> columnsList = columnsMapper.findColumnsByShow(map);
        Table table = new Table("",columnsList);
        List<OperaSb> operaSbList = operaSbMapper.findOperaSbByShow(map);
        List<OperaIn> operaInList = operaInMapper.findOperaInByShow(map);
        Opera opera = new Opera(operaSbList,dispOperaIn(operaInList));
        return new TableOpera(opera,table);
    }

    private List<List<OperaIn>> dispOperaIn(List<OperaIn> list){
        List<List<OperaIn>> data = new LinkedList<>();
        List<OperaIn> temp = new ArrayList<>();
        boolean is = list.size() % 2 != 0;
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
        if(is){
            data.add(temp);
        }
        return data;
    }
}
