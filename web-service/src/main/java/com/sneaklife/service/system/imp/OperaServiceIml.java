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

import java.util.*;

@Service
public class OperaServiceIml implements OperaService {

    @Autowired
    private ColumnsMapper columnsMapper;

    @Autowired
    private OperaInMapper operaInMapper;

    @Autowired
    private OperaSbMapper operaSbMapper;

    private volatile int size = 1;

    private volatile List<Map<String, Object>> data = new LinkedList<>();

    @Override
    public TableOpera buildOperaBody(Map<String, Object> map, boolean is) {
        List<Columns> columnsList = columnsMapper.findColumnsByShow(map);
        Table table = new Table("",columnsList);
        List<OperaSb> operaSbList = operaSbMapper.findOperaSbByShow(map);
        List<OperaIn> operaInList = operaInMapper.findOperaInByShow(map);
        Opera opera = new Opera(operaSbList,dispOperaIn(operaInList,is));
        return new TableOpera(opera,table);
    }

    @Override
    public List<Map<String, Object>> buildOperaTreeGrid(Map<String, Object> map) {
        data.add(buildOperaItem(String.valueOf(map.get("name")), size, size - 1,1,true));
        int p = size;
        buildOperaColumnsTree(map, p);
        buildOperaSbTree(map, p);
        buildOperaInTree(map, p);
        return data;
    }

    @Override
    public void clean(){
        size = 1;
        data = new LinkedList<>();
    }

    private Map<String,Object> buildOperaItem(String name,int id,int pid,int status,boolean check){
        Map<String,Object> item = new HashMap<>();
        item.put("id",id);
        item.put("status",status);
        item.put("check",check);
        item.put("pid",pid);
        item.put("name",name);
        return item;
    }

    private void buildOperaColumnsTree(Map<String, Object> map, int pid){
        data.add(buildOperaItem("OperaColumns",++size, pid,1,true));
        int p = size;
        List<Columns> columnsList = columnsMapper.findColumnsByShow(map);
        columnsList.forEach(columns -> data.add(buildOperaItem(columns.getTitle(),++size, p,1,true)));
    }

    private void buildOperaSbTree(Map<String, Object> map, int pid){
        data.add(buildOperaItem("OperaSb", ++size, pid,1,true));
        int p = size;
        List<OperaSb> operaSbList = operaSbMapper.findOperaSbByShow(map);
        operaSbList.forEach(operaSb -> data.add(buildOperaItem(operaSb.getText(), ++size, p,1,true)));
    }

    private void buildOperaInTree(Map<String, Object> map, int pid){
        data.add(buildOperaItem("OperaIn", ++size, pid,1,true));
        int p = size;
        List<OperaIn> operaInList = operaInMapper.findOperaInByShow(map);
        operaInList.forEach(operaIn -> data.add(buildOperaItem(operaIn.getTextName(), ++size, p,1,true)));
    }

    private List<List<OperaIn>> dispOperaIn(List<OperaIn> list, boolean t){
        List<List<OperaIn>> data = new LinkedList<>();
        if(t){
            data.add(list);
            return data;
        }
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
