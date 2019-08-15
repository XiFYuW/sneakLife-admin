package com.sneaklife.service.system.imp;

import com.sneaklife.common.CommonUtil;
import com.sneaklife.dao.entity.*;
import com.sneaklife.dao.entity.modal.Opera;
import com.sneaklife.dao.entity.modal.Table;
import com.sneaklife.dao.entity.modal.TableOpera;
import com.sneaklife.dao.system.SystemMenuMapper;
import com.sneaklife.dao.system.authority.opera.ColumnsMapper;
import com.sneaklife.dao.system.authority.opera.OperaInMapper;
import com.sneaklife.dao.system.authority.opera.OperaSbMapper;
import com.sneaklife.dao.system.authority.roleConfig.RoleConfigMapper;
import com.sneaklife.exception.SneakLifeException;
import com.sneaklife.service.system.OperaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author https://github.com/XiFYuW
 */
@Service
@SuppressWarnings("unchecked")
public class OperaServiceImp implements OperaService {

    @Autowired
    private ColumnsMapper columnsMapper;

    @Autowired
    private OperaInMapper operaInMapper;

    @Autowired
    private OperaSbMapper operaSbMapper;

    @Autowired
    private SystemMenuMapper systemMenuMapper;

    @Autowired
    private RoleConfigMapper roleConfigMapper;

    private static Logger log = LoggerFactory.getLogger(OperaServiceImp.class);

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
    public List<Map<String, Object>> buildRoleFunction(RoleFunction roleFunction, Map<String, Object> map) {
        RoleConfig roleConfig = roleConfigMapper.getById(String.valueOf(map.get("menuId")));
        data.add(buildOperaItem(roleConfig.getId(), roleConfig.getName(), size, size - 1, 0, true));
        List<String> menuId;
        if(CommonUtil.isNull(roleFunction)){
            menuId = Arrays.asList(roleFunction.getMenuId().split(","));
        }else {
            menuId = new ArrayList<>();
        }
        List<SystemMenu> systemMenuList = systemMenuMapper.getByIsDel(0);
        int len = systemMenuList.size();
        int p = size;
        for (int i = 0; i < len; i++) {
            SystemMenu systemMenu = systemMenuList.get(i);
            SystemMenu parentMenu = findChildMenu(systemMenu, systemMenuList, menuId, p);
            len = removeNode(parentMenu, systemMenuList, menuId, len);
        }
        return data;
    }

    @Override
    public void clean(){
        size = 1;
        data = new LinkedList<>();
    }

    /**
     * Build function body options
     * @param treeViewId View display treeViewId
     * @param name View display name
     * @param id View display id
     * @param pid View display pid
     * @param status View display status
     * @param check View display check
     * @return a options data
     */
    private Map<String,Object> buildOperaItem(String treeViewId, String name, int id, int pid, int status, boolean check){
        Map<String,Object> item = new HashMap<>();
        item.put("id",id);
        item.put("status",status);
        item.put("check",check);
        item.put("pid",pid);
        item.put("name",name);
        item.put("treeViewId",treeViewId);
        return item;
    }

    /**
     * Build function fields
     * @param map Option data
     * @param pid View display pid
     * @param num Number of function fields
     */
    private void buildOperaColumnsTree(Map<String, Object> map, int pid, int num){
        data.add(buildOperaItem(OPERA_COLUMNS, "OperaColumns", ++size, pid, num > 0 ? 0 : 1, num > 0));
        int p = size;
        List<Columns> columnsList = columnsMapper.findColumnsByShow(map);
        columnsList.forEach(columns -> data.add(buildOperaItem(columns.getId(), columns.getTitle(),++size, p, columns.getIsShow(),columns.getIsShow() == 0)));
    }

    /**
     * Build function button
     * @param map Option data
     * @param pid View display pid
     * @param num Number of function button
     */
    private void buildOperaSbTree(Map<String, Object> map, int pid, int num){
        data.add(buildOperaItem(OPERA_SB,"OperaSb", ++size, pid, num > 0 ? 0 : 1,num > 0));
        int p = size;
        List<OperaSb> operaSbList = operaSbMapper.findOperaSbByShow(map);
        operaSbList.forEach(operaSb -> data.add(buildOperaItem(operaSb.getId(),operaSb.getText(), ++size, p, operaSb.getIsShow(),operaSb.getIsShow() == 0)));
    }

    /**
     * Build function input
     * @param map Option data
     * @param pid View display pid
     * @param num Number of function input
     */
    private void buildOperaInTree(Map<String, Object> map, int pid, int num){
        data.add(buildOperaItem(OPERA_IN,"OperaIn", ++size, pid, num > 0 ? 0 : 1,num > 0));
        int p = size;
        List<OperaIn> operaInList = operaInMapper.findOperaInByShow(map);
        operaInList.forEach(operaIn -> data.add(buildOperaItem(operaIn.getId(),operaIn.getTextName(), ++size, p, operaIn.getIsShow(),operaIn.getIsShow() == 0)));
    }

    /**
     * Process the display of function input at the front end
     * @param list List to display
     * @param t Whether to return directly
     * @return Function input options displayed on the front end
     */
    private List<List<OperaIn>> dispOperaIn(List<OperaIn> list, boolean t){
        List<List<OperaIn>> data = new LinkedList<>();
        if(t){
            data.add(list);
            return data;
        }
        // The processing measure is 2
        List<OperaIn> temp = new ArrayList<>();
        boolean odd = list.size() % 2 != 0;
        for (int i = 1; i <= list.size(); i++) {
            OperaIn operaIn = list.get(i - 1);
            if(i % 2 != 0){
                temp.add(operaIn);
            }else {
                temp.add(operaIn);
                data.add(temp);
                temp = new ArrayList<>();
            }
        }
        // Odd Numbers, let's do the last one
        if(odd){
            data.add(temp);
        }
        return data;
    }

    /**
     * Add elements based on key
     * @param key A primary key
     * @param data Data list
     * @param map The element
     */
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

    /**
     * Change the status according to check
     * @param map The element
     */
    private void updateStatus(Map<String,Object> map){
        if(!map.containsKey("check")){
            map.put("status",1);
        }else {
            map.put("status",0);
        }
    }

    /**
     * Control modify function field, function button, function input
     * @param str Determine the functional options to modify
     * @param map The element
     * @param data Data list
     * @param isUpdate Whether to perform a modification action for recursive invocation
     * @return Whether an option is a functional option
     */
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
                // Illegal node detection
                if(checkIllegalNode(map)){
                    del = true;
                    break;
                }
                // The root node
                if("0".equals(String.valueOf(map.get("pid")))){
                    del = true;
                    pushByKey(OPERA, data, map);
                    break;
                }
                // Perform related modification actions
                Set<String> set = data.keySet();
                for (String keys : set) {
                    List<Map<String, Object>> items = data.get(keys);
                    for (Map<String, Object> mi : items) {
                        if (String.valueOf(map.get("pid")).equals(String.valueOf(mi.get("id")))) {
                            del = switchOpera(keys, map, data, true);
                        }
                    }
                }
        }
        return del;
    }

    /**
     * Handle update action
     * @param upList List to be updated
     * @param data Data list
     * @throws SneakLifeException
     */
    private void dispUpdate(List<Map<String,Object>> upList,Map<String,List<Map<String,Object>>> data) throws SneakLifeException{
        for (Iterator<Map<String, Object>> it = upList.iterator(); it.hasNext();){
            Map<String,Object> map = it.next();
            boolean bl = switchOpera(String.valueOf(map.get("treeViewId")), map, data, false);
            if(bl){
                it.remove();
            }
        }
        // Resolve list unordering
        if(upList.size() > 0){
            dispUpdate(upList,data);
        }
        throw new SneakLifeException(CommonUtil.respResultXGCG());
    }

    /**
     * Detect illegal node
     * @param map The element
     * @return Illegal node
     */
    private boolean checkIllegalNode(Map<String,Object> map){
        int cm = columnsMapper.checkColumnsById(map);
        int oim = operaInMapper.checkOperaInById(map);
        int obm = operaSbMapper.checkOperaSbById(map);
        return cm + oim + obm == 0;
    }

    /**
     * Remove duplicate nodes from all nodes
     * @param parentMenu Delete the item
     * @param list All the nodes
     * @param menuId Roles already have function menus
     * @param size The size of all nodes, can change the list length, do not need to pass 0
     * @return Residual size of all nodes
     */
    private int removeNode(SystemMenu parentMenu, List<SystemMenu> list, List<String> menuId, int size){
        List<SystemMenu> childMenu = parentMenu.getSon();
        for (SystemMenu child : childMenu) {
            Iterator<SystemMenu> it = list.iterator();
            while (it.hasNext()) {
                SystemMenu menu = it.next();
                if (child.getId().equals(menu.getId())) {
                    it.remove();
                    size--;
                }
                if(menuId.contains(child.getId())){
                    data.forEach(map -> {
                        if(child.getPid().equals(map.get("treeViewId"))){
                            map.put("check", true);
                            map.put("status", 0);
                        }
                    });
                }
            }
            size = removeNode(child, list, menuId, size);
        }
        return size;
    }

    /**
     * Find child node
     * @param parent The parent node
     * @param list All the nodes
     * @param menuId Roles already have function menus
     * @param p Parent id
     * @return Parent node tape node
     */
    private SystemMenu findChildMenu(SystemMenu parent, List<SystemMenu> list, List<String> menuId, int p){
        List<SystemMenu> childMenu = new ArrayList<>();
        if(menuId.contains(parent.getId())){
            data.add(buildOperaItem(parent.getId(), parent.getTab(), ++size, p, 0, true));
        }else {
            data.add(buildOperaItem(parent.getId(), parent.getTab(), ++size, p, 1, false));
        }
        p = size;
        for (SystemMenu menu : list) {
            if (parent.getId().equals(menu.getPid())) {
                SystemMenu child = findChildMenu(menu, list, menuId, p);
                childMenu.add(child);
                parent.setSon(childMenu);
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("menuId", parent.getId());
        int s = size;
        buildOperaColumnsTree(map, s, 0);
        buildOperaSbTree(map, s, 0);
        buildOperaInTree(map, s, 0);
        return parent;
    }
}
