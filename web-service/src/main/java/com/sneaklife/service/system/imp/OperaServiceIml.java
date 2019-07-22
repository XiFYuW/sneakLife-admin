package com.sneaklife.service.system.imp;

import com.sneaklife.common.CommonUtil;
import com.sneaklife.dao.entity.Columns;
import com.sneaklife.dao.entity.OperaIn;
import com.sneaklife.dao.entity.OperaSb;
import com.sneaklife.dao.entity.modal.Opera;
import com.sneaklife.dao.entity.modal.Table;
import com.sneaklife.dao.entity.modal.TableOpera;
import com.sneaklife.dao.system.columns.ColumnsMapper;
import com.sneaklife.dao.system.opera.OperaInMapper;
import com.sneaklife.dao.system.opera.OperaSbMapper;
import com.sneaklife.exception.SneakLifeException;
import com.sneaklife.service.system.OperaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
@SuppressWarnings("unchecked")
public class OperaServiceIml implements OperaService {

    @Autowired
    private ColumnsMapper columnsMapper;

    @Autowired
    private OperaInMapper operaInMapper;

    @Autowired
    private OperaSbMapper operaSbMapper;

    private static Logger log = LoggerFactory.getLogger(OperaServiceIml.class);

    private static final String OPERA_COLUMNS = "opera-columns";

    private static final String OPERA_IN = "opera-in";

    private static final String OPERA_SB = "opera-sb";

    private static final String OPERA = "opera";

    private volatile int size = 1;

    private volatile List<Map<String, Object>> data = new LinkedList<>();

    @Override
    public void updateOpera(Map<String, Object> map) throws Exception {
        List<Map<String,Object>> upList = (List<Map<String,Object>>) map.get("up");
        Map<String,List<Map<String,Object>>> data = dispUp(upList);

        throw new SneakLifeException(CommonUtil.respResultXGCG());
    }

    @Override
    public void insertOperaSb(OperaSb operaSb) throws Exception{
        int t = operaSbMapper.insertOperaSb(operaSb);
        if(t != 1){
            throw new SneakLifeException(CommonUtil.respResultTJSB());
        }
        throw new SneakLifeException(CommonUtil.respResultTJCG());
    }

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
        data.add(buildOperaItem(String.valueOf(map.get("menuId")),String.valueOf(map.get("name")), size, size - 1,1,true));
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

    private Map<String,Object> buildOperaItem(String treeViewId,String name,int id,int pid,int status,boolean check){
        Map<String,Object> item = new HashMap<>();
        item.put("id",id);
        item.put("status",status);
        item.put("check",check);
        item.put("pid",pid);
        item.put("name",name);
        item.put("treeViewId",treeViewId);
        return item;
    }

    private void buildOperaColumnsTree(Map<String, Object> map, int pid){
        data.add(buildOperaItem(OPERA_COLUMNS,"OperaColumns",++size, pid,1,true));
        int p = size;
        List<Columns> columnsList = columnsMapper.findColumnsByShow(map);
        columnsList.forEach(columns -> data.add(buildOperaItem(columns.getId(),columns.getTitle(),++size, p,1,true)));
    }

    private void buildOperaSbTree(Map<String, Object> map, int pid){
        data.add(buildOperaItem(OPERA_SB,"OperaSb", ++size, pid,1,true));
        int p = size;
        List<OperaSb> operaSbList = operaSbMapper.findOperaSbByShow(map);
        operaSbList.forEach(operaSb -> data.add(buildOperaItem(operaSb.getId(),operaSb.getText(), ++size, p,1,true)));
    }

    private void buildOperaInTree(Map<String, Object> map, int pid){
        data.add(buildOperaItem(OPERA_IN,"OperaIn", ++size, pid,1,true));
        int p = size;
        List<OperaIn> operaInList = operaInMapper.findOperaInByShow(map);
        operaInList.forEach(operaIn -> data.add(buildOperaItem(operaIn.getId(),operaIn.getTextName(), ++size, p,1,true)));
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

    private void pushByKey(String key, String keys, Map<String,List<Map<String,Object>>> data, Map<String,Object> map){
        List<Map<String,Object>> list;
        if("".equals(keys)){
            if(!data.containsKey(key)){
                list = new ArrayList<>();
                list.add(map);
                data.put(key,list);
            }else {
                list = data.get(key);
                list.add(map);
                data.put(key,list);
            }
        }else {
            Map<String,Object> item = data.get(keys).get(0);
            if(!item.containsKey(key)){
                list = new ArrayList<>();
                list.add(map);
                item.put(key,list);
            }else{
                list = (List<Map<String,Object>>) item.get(key);
                list.add(map);
                item.put(key,list);
            }
        }

    }

    private Map<String,List<Map<String,Object>>> dispUp(List<Map<String,Object>> upList){
        Map<String,List<Map<String,Object>>> data = new HashMap<>();
        for (Iterator<Map<String, Object>> it = upList.iterator(); it.hasNext();){
            Map<String,Object> map = it.next();
            switch (String.valueOf(map.get("treeViewId"))){
                case OPERA_COLUMNS:
                    pushByKey(OPERA_COLUMNS, "", data, map);
                    it.remove();
                    break;
                case OPERA_IN:
                    pushByKey(OPERA_IN, "", data, map);
                    it.remove();
                    break;
                case OPERA_SB:
                    pushByKey(OPERA_SB, "", data, map);
                    it.remove();
                    break;
                default:
                    if("0".equals(String.valueOf(map.get("pid")))){
                        pushByKey(OPERA, "", data, map);
                        it.remove();
                        break;
                    }
                    // 执行相关的修改操作  批量？单个？
                    Set<String> set = data.keySet();
                    for(Iterator<String> key = set.iterator(); key.hasNext();){
                        String keys = key.next();
                        List<Map<String,Object>> items = data.get(keys);
                        for(Iterator<Map<String,Object>> item = items.iterator(); item.hasNext();){
                            Map<String,Object> mi = item.next();
                            if(String.valueOf(map.get("pid")).equals(String.valueOf(mi.get("id")))){
                                pushByKey("son", keys, data, map);
                            }
                        }
                    }
                    it.remove();
            }
        }
        return data;
    }
}
