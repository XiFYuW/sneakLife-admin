package com.sneaklife.pms.service.common.imp;

import com.sneaklife.pms.dao.system.SystemMenuMapper;
import com.sneaklife.pms.dao.system.authority.opera.ColumnsMapper;
import com.sneaklife.pms.dao.system.authority.opera.OperaInMapper;
import com.sneaklife.pms.dao.system.authority.opera.OperaSbMapper;
import com.sneaklife.pms.dao.system.authority.roleConfig.RoleConfigMapper;
import com.sneaklife.pms.entity.*;
import com.sneaklife.pms.entity.modal.Opera;
import com.sneaklife.pms.entity.modal.Table;
import com.sneaklife.pms.entity.modal.TableOpera;
import com.sneaklife.pms.service.common.OperaService;
import com.sneaklife.ut.iws.IwsContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    private volatile List<Map<String, Object>> data = new ArrayList<>();

    @Override
    public TableOpera buildOperaBody(Map<String, Object> map, boolean is) {
        List<Columns> columnsList = columnsMapper.findColumnsByShow(map);
        Table table = new Table(columnsList);
        List<Map<String,Object>> operaSbList = operaSbMapper.findOperaSbByShow(map);
        List<OperaIn> operaInList = operaInMapper.findOperaInByShow(map);
        Opera opera = new Opera(operaSbList,dispOperaIn(operaInList,is));
        return new TableOpera(table,opera);
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
        List<String> roleFunctionMenuId;
        if(IwsContext.isNull(roleFunction)){
            roleFunctionMenuId = Arrays.asList(roleFunction.getMenuId().split(","));
        }else {
            roleFunctionMenuId = new ArrayList<>();
        }
        List<SystemMenu> systemMenuList = systemMenuMapper.getByIsDel(0);
        int len = systemMenuList.size();
        int p = size;
        for (int i = 0; i < len; i++) {
            SystemMenu systemMenu = systemMenuList.get(i);
            SystemMenu parentMenu = findChildMenu(systemMenu, systemMenuList, roleFunctionMenuId, p,true);
            len = removeChildNode(parentMenu, systemMenuList, roleFunctionMenuId, len);
            i--;
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
        List<Map<String,Object>> operaSbList = operaSbMapper.findOperaSbByShow(map);
        operaSbList.forEach(operaSb -> {
            String id = String.valueOf(operaSb.get("id"));
            String codeName = String.valueOf(operaSb.get("codeName"));
            int isShow = Integer.valueOf(String.valueOf(operaSb.get("isShow")));
            data.add(buildOperaItem(id, codeName, ++size, p, isShow,isShow == 0));
        });
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
     * Remove duplicate nodes from all nodes
     * @param parentMenu Delete the item
     * @param list All the nodes
     * @param roleFunctionMenuId Roles already have function menus
     * @param size The size of all nodes, can change the list length, do not need to pass 0
     * @return Residual size of all nodes
     */
    private int removeChildNode(SystemMenu parentMenu, List<SystemMenu> list, List<String> roleFunctionMenuId, int size){
        List<SystemMenu> childMenu = parentMenu.getSon();
        if(childMenu.size() <= 0){
            return removeNode(parentMenu, list, size);
        }

        for (SystemMenu child : childMenu) {
            Iterator<SystemMenu> it = list.iterator();
            while (it.hasNext()) {
                SystemMenu menu = it.next();
                if (child.getId().equals(menu.getId()) || parentMenu.getId().equals(menu.getId())) {
                    it.remove();
                    size--;
                }
                if(roleFunctionMenuId.contains(child.getId())){
                    data.forEach(map -> {
                        if(child.getPid().equals(map.get("treeViewId"))){
                            map.put("check", true);
                            map.put("status", 0);
                        }
                    });
                }
            }
            size = removeChildNode(child, list, roleFunctionMenuId, size);
        }
        return size;
    }

    /**
     * Find child node
     * @param parent The parent node
     * @param list All the nodes
     * @param roleFunctionMenuId Roles already have function menus
     * @param p Parent id
     * @return Parent node tape node
     */
    private SystemMenu findChildMenu(SystemMenu node, List<SystemMenu> list, List<String> roleFunctionMenuId, int p, boolean parent){
        List<SystemMenu> childMenu = new ArrayList<>();
        if(roleFunctionMenuId.contains(node.getId())){
            data.add(buildOperaItem(node.getId(), node.getTab(), ++size, p, 0, true));
        }else {
            data.add(buildOperaItem(node.getId(), node.getTab(), ++size, p, 1, false));
        }
        p = size;
        for (SystemMenu menu : list) {
            // 为根节点
            if (node.getId().equals(menu.getPid())) {
                SystemMenu child = findChildMenu(menu, list, roleFunctionMenuId, p,false);
                childMenu.add(child);
                node.setSon(childMenu);
            }

            // 为子节点
            if(parent && node.getPid().equals(menu.getId())){
                synchronized (OPERA){
                    Iterator<Map<String,Object>> iterator = data.iterator();
                    while (iterator.hasNext()){
                        Map<String,Object> m = iterator.next();
                        if(m.get("treeViewId").equals(node.getId())){
                            iterator.remove();
                            size--;
                        }
                    }
                }
                p = 1;
                return findChildMenu(menu, list, roleFunctionMenuId, p,false);
            }
        }

        Map<String, Object> map = new HashMap<>();
        map.put("menuId", node.getId());
        int s = size;
        buildOperaColumnsTree(map, s, 1);
        buildOperaSbTree(map, s, 1);
        buildOperaInTree(map, s, 1);
        return node;
    }

    @Override
    public List<Map<String, Object>> getSelectsKyByMenuId(String menuId) {
        return operaInMapper.getSelectsKyByMenuId(menuId);
    }

    @Override
    public int removeNode(SystemMenu parentMenu, List<SystemMenu> list, int size) {
        Iterator<SystemMenu> it = list.iterator();
        while (it.hasNext()) {
            SystemMenu menu = it.next();
            boolean isRemove = parentMenu.getId().equals(menu.getId());
            if (isRemove) {
                it.remove();
                size--;
            }
        }
        return size;
    }
}