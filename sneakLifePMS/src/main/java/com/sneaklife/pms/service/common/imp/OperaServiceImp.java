package com.sneaklife.pms.service.common.imp;

import com.sneaklife.pms.config.log.SneakLifeAnLog;
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
import com.sneaklife.ut.common.CommonUtil;
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
    @SneakLifeAnLog
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

    @Override
    public List<Map<String, Object>> getSelectsKyByMenuId(String menuId) {
        return operaInMapper.getSelectsKyByMenuId(menuId);
    }
}
