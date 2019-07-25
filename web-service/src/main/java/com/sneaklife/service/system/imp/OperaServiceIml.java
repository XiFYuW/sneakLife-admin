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
import org.springframework.transaction.annotation.Transactional;

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
    public void deleteOpera(Map<String, Object> map) throws Exception {
        throw new SneakLifeException(CommonUtil.respResultSCCG());
    }

    @Override
    @Transactional
    public void updateOpera(Map<String, Object> map) throws Exception {
        List<Map<String,Object>> upList = (List<Map<String,Object>>) map.get("up");
        dispUpdate(upList,new HashMap<>());
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
        int numColumns = columnsMapper.checkColumnsByShow(map);
        int numOperaSb = operaSbMapper.checkOperaSbByShow(map);
        int numOperaIn = operaInMapper.checkOperaInByShow(map);
        int sun = numColumns + numOperaSb + numOperaIn;
        data.add(buildOperaItem(String.valueOf(map.get("menuId")),String.valueOf(map.get("name")), size,size - 1, sun > 0 ? 0 : 1,sun > 0));
        int p = size;
        buildOperaColumnsTree(map, p, numColumns);
        buildOperaSbTree(map, p, numOperaSb);
        buildOperaInTree(map, p, numOperaIn);
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

    private void buildOperaColumnsTree(Map<String, Object> map, int pid, int num){
        data.add(buildOperaItem(OPERA_COLUMNS, "OperaColumns", ++size, pid, num > 0 ? 0 : 1, num > 0));
        int p = size;
        List<Columns> columnsList = columnsMapper.findColumnsByShow(map);
        columnsList.forEach(columns -> data.add(buildOperaItem(columns.getId(), columns.getTitle(),++size, p, columns.getIsShow(),columns.getIsShow() == 0)));
    }

    private void buildOperaSbTree(Map<String, Object> map, int pid, int num){
        data.add(buildOperaItem(OPERA_SB,"OperaSb", ++size, pid, num > 0 ? 0 : 1,num > 0));
        int p = size;
        List<OperaSb> operaSbList = operaSbMapper.findOperaSbByShow(map);
        operaSbList.forEach(operaSb -> data.add(buildOperaItem(operaSb.getId(),operaSb.getText(), ++size, p, operaSb.getIsShow(),operaSb.getIsShow() == 0)));
    }

    private void buildOperaInTree(Map<String, Object> map, int pid, int num){
        data.add(buildOperaItem(OPERA_IN,"OperaIn", ++size, pid, num > 0 ? 0 : 1,num > 0));
        int p = size;
        List<OperaIn> operaInList = operaInMapper.findOperaInByShow(map);
        operaInList.forEach(operaIn -> data.add(buildOperaItem(operaIn.getId(),operaIn.getTextName(), ++size, p, operaIn.getIsShow(),operaIn.getIsShow() == 0)));
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

    private void pushByKey(String key, Map<String,List<Map<String,Object>>> data, Map<String,Object> map){
        List<Map<String,Object>> list;
        if(!data.containsKey(key)){
            list = new ArrayList<>();
            list.add(map);
            data.put(key,list);
        }else {
            list = data.get(key);
            list.add(map);
            data.put(key,list);
        }
    }

    private void updateStatus(Map<String,Object> map){
        if(!map.containsKey("check")){
            map.put("status",1);
        }else {
            map.put("status",0);
        }
    }

    private boolean switchOpera(String str, Map<String,Object> map, Map<String,List<Map<String,Object>>> data, boolean isUpdate){
        boolean del = false;
        switch (str){
            case OPERA_COLUMNS:
                del = true;
                if(isUpdate){
                    updateStatus(map);
                    columnsMapper.updateColumnsShow(map);
                    break;
                }
                pushByKey(OPERA_COLUMNS, data, map);
                break;
            case OPERA_IN:
                del = true;
                if(isUpdate){
                    updateStatus(map);
                    operaInMapper.updateOperaInShow(map);
                    break;
                }
                pushByKey(OPERA_IN, data, map);
                break;
            case OPERA_SB:
                del = true;
                if(isUpdate){
                    updateStatus(map);
                    operaSbMapper.updateOperaSbShow(map);
                    break;
                }
                pushByKey(OPERA_SB, data, map);
                break;
            default:
                // 非法节点检测
                if(checkIllegalNode(map)){
                    del = true;
                    break;
                }
                // 根节点
                if("0".equals(String.valueOf(map.get("pid")))){
                    del = true;
                    pushByKey(OPERA, data, map);
                    break;
                }
                // 执行相关的修改操作
                Set<String> set = data.keySet();
                for(Iterator<String> key = set.iterator(); key.hasNext();){
                    String keys = key.next();
                    List<Map<String,Object>> items = data.get(keys);
                    for(Iterator<Map<String,Object>> item = items.iterator(); item.hasNext();){
                        Map<String,Object> mi = item.next();
                        if(String.valueOf(map.get("pid")).equals(String.valueOf(mi.get("id")))){
                            del = switchOpera(keys, map, data, true);
                        }
                    }
                }
        }
        return del;
    }

    private void dispUpdate(List<Map<String,Object>> upList,Map<String,List<Map<String,Object>>> data) throws SneakLifeException{
        for (Iterator<Map<String, Object>> it = upList.iterator(); it.hasNext();){
            Map<String,Object> map = it.next();
            boolean bl = switchOpera(String.valueOf(map.get("treeViewId")), map, data, false);
            if(bl){
                it.remove();
            }
        }
        // 解决列表无序
        if(upList.size() > 0){
            dispUpdate(upList,data);
        }
        throw new SneakLifeException(CommonUtil.respResultXGCG());
    }

    private boolean checkIllegalNode(Map<String,Object> map){
        int cm = columnsMapper.checkColumnsById(map);
        int oim = operaInMapper.checkOperaInById(map);
        int obm = operaSbMapper.checkOperaSbById(map);
        return cm + oim + obm == 0;
    }
}
