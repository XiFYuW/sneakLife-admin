package com.sneaklife.pms.service.common.imp;

import com.sneaklife.config.SneakLifeSystemEnum;
import com.sneaklife.pms.dao.system.SystemMenuMapper;
import com.sneaklife.pms.dao.system.authority.opera.ColumnsMapper;
import com.sneaklife.pms.dao.system.authority.opera.OperaBoMapper;
import com.sneaklife.pms.dao.system.authority.opera.OperaInMapper;
import com.sneaklife.pms.dao.system.authority.opera.OperaSbMapper;
import com.sneaklife.pms.dao.system.authority.roleConfig.RoleConfigMapper;
import com.sneaklife.pms.entity.*;
import com.sneaklife.pms.entity.modal.Opera;
import com.sneaklife.pms.entity.modal.Table;
import com.sneaklife.pms.entity.modal.TableOpera;
import com.sneaklife.pms.service.common.OperaService;
import com.sneaklife.pms.service.common.RedisService;
import com.sneaklife.ut.iws.IwsContext;
import com.sneaklife.ut.log.LogicalLogAn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author https://github.com/XiFYuW
 */
@Service
public class OperaServiceImp implements OperaService {

    private final ColumnsMapper columnsMapper;

    private final OperaInMapper operaInMapper;

    private final OperaSbMapper operaSbMapper;

    private final OperaBoMapper operaBoMapper;

    private final SystemMenuMapper systemMenuMapper;

    private final RoleConfigMapper roleConfigMapper;

    private final RedisService redisService;

    private static Logger log = LoggerFactory.getLogger(OperaServiceImp.class);

    private volatile int size = 1;

    private volatile List<Map<String, Object>> data = new ArrayList<>();

    @Autowired
    public OperaServiceImp(ColumnsMapper columnsMapper, OperaInMapper operaInMapper, OperaSbMapper operaSbMapper,
                           OperaBoMapper operaBoMapper, SystemMenuMapper systemMenuMapper, RoleConfigMapper roleConfigMapper,
                           RedisService redisService) {
        this.columnsMapper = columnsMapper;
        this.operaInMapper = operaInMapper;
        this.operaSbMapper = operaSbMapper;
        this.operaBoMapper = operaBoMapper;
        this.systemMenuMapper = systemMenuMapper;
        this.roleConfigMapper = roleConfigMapper;
        this.redisService = redisService;
    }

    @Override
    @LogicalLogAn
    public TableOpera buildOperaBody(Map<String, Object> map, boolean is) throws Exception{
        List<String> userInfoList = redisService.getLoginUserOpera();
        List<Columns> columnsList = getOperaBodyColumns(userInfoList, map);
        Table table = new Table(columnsList);
        List<Map<String,Object>> operaSbList = getOperaBodySb(userInfoList, map);
        List<OperaIn> operaInList = getOperaBodyIn(userInfoList, map);
        List<OperaBo> operaBoList = getOperaBodyBo(userInfoList, map);
        Opera opera = new Opera(operaSbList, displayOpera(operaInList, 2, is), displayOpera(operaBoList, 3, is));
        return new TableOpera(table,opera);
    }

    private List<Columns> getOperaBodyColumns(List<String> userInfoList, Map<String, Object> map){
        List<Columns> columnsList = columnsMapper.findColumns(map);
        columnsList = columnsList.stream().filter(columns -> userInfoList.contains(columns.getId())).collect(Collectors.toList());
        return columnsList;
    }

    private List<Map<String,Object>> getOperaBodySb(List<String> userInfoList, Map<String, Object> map){
        List<Map<String,Object>> operaSbList = operaSbMapper.findOperaSb(map);
        operaSbList = operaSbList.stream().filter(sb -> userInfoList.contains(sb.get("id").toString())).collect(Collectors.toList());
        return operaSbList;
    }

    private List<OperaIn> getOperaBodyIn(List<String> userInfoList, Map<String, Object> map){
        List<OperaIn> operaInList = operaInMapper.findOperaIn(map);
        operaInList = operaInList.stream().filter(in -> userInfoList.contains(in.getId())).collect(Collectors.toList());
        return operaInList;
    }

    private List<OperaBo> getOperaBodyBo(List<String> userInfoList, Map<String, Object> map){
        List<OperaBo> operaBoList = operaBoMapper.findOperaBo(map);
        operaBoList = operaBoList.stream().filter(bo -> userInfoList.contains(bo.getId())).collect(Collectors.toList());
        return operaBoList;
    }

    @Override
    @LogicalLogAn
    public void clean(){
        size = 1;
        data = new LinkedList<>();
    }

    @Override
    @LogicalLogAn
    public List<Map<String, Object>> buildRoleFunction(RoleFunction roleFunction, Map<String, Object> map) {
        RoleConfig roleConfig = roleConfigMapper.getById(String.valueOf(map.get("menuId")));
        data.add(buildOperaItem(roleConfig.getId(), roleConfig.getName(), size, size - 1, 0, true));
        List<String> roleFunctionMenuId;
        if(IwsContext.isNotNull(roleFunction)){
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
                synchronized (SneakLifeSystemEnum.OPERA.toName()){
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
        buildOperaColumnsTree(map, s, roleFunctionMenuId);
        buildOperaSbTree(map, s, roleFunctionMenuId);
        buildOperaInTree(map, s, roleFunctionMenuId);
        buildOperaBoTree(map, s, roleFunctionMenuId);
        return node;
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
     * @param roleFunctionMenuId Roles already have function menus
     */
    private void buildOperaColumnsTree(Map<String, Object> map, int pid, List<String> roleFunctionMenuId){
        int z = ++size;
        int p = size;
        boolean one = false;
        List<Columns> columnsList = columnsMapper.findColumns(map);
        for (Columns columns : columnsList) {
            if(roleFunctionMenuId.contains(columns.getId())){
                data.add(buildOperaItem(columns.getId(), columns.getTitle(),++size, p, 0,true));
                one = true;
            }else {
                data.add(buildOperaItem(columns.getId(), columns.getTitle(),++size, p, 1,false));
            }
        }
        if(one){
            data.add(buildOperaItem(SneakLifeSystemEnum.OPERA_COLUMNS.toName(), SneakLifeSystemEnum.OPERA_COLUMNS_VALUE.toName(), z, pid, 0, true));
        }else if(columnsList.size() > 0) {
            data.add(buildOperaItem(SneakLifeSystemEnum.OPERA_COLUMNS.toName(), SneakLifeSystemEnum.OPERA_COLUMNS_VALUE.toName(), z, pid, 1, false));
        }
    }

    /**
     * Build function button
     * @param map Option data
     * @param pid View display pid
     * @param roleFunctionMenuId Roles already have function menus
     */
    private void buildOperaSbTree(Map<String, Object> map, int pid, List<String> roleFunctionMenuId){
        int z = ++size;
        int p = size;
        boolean one = false;
        List<Map<String,Object>> operaSbList = operaSbMapper.findOperaSb(map);
        for (Map<String,Object> operaSb : operaSbList) {
            String id = String.valueOf(operaSb.get("id"));
            String codeName = String.valueOf(operaSb.get("codeName"));
            if(roleFunctionMenuId.contains(id)){
                data.add(buildOperaItem(id, codeName, ++size, p, 0,true));
                one = true;
            }else {
                data.add(buildOperaItem(id, codeName, ++size, p, 1,false));
            }
        }
        if(one){
            data.add(buildOperaItem(SneakLifeSystemEnum.OPERA_SB.toName(), SneakLifeSystemEnum.OPERA_SB_VALUE.toName(), z, pid, 0,true));
        }else if(operaSbList.size() > 0){
            data.add(buildOperaItem(SneakLifeSystemEnum.OPERA_SB.toName(), SneakLifeSystemEnum.OPERA_SB_VALUE.toName(), z, pid, 1,false));
        }
    }

    /**
     * Build function input
     * @param map Option data
     * @param pid View display pid
     * @param roleFunctionMenuId Roles already have function menus
     */
    private void buildOperaInTree(Map<String, Object> map, int pid, List<String> roleFunctionMenuId){
        int z = ++size;
        int p = size;
        boolean one = false;
        List<OperaIn> operaInList = operaInMapper.findOperaIn(map);
        for (OperaIn operaIn : operaInList) {
            if(roleFunctionMenuId.contains(operaIn.getId())){
                data.add(buildOperaItem(operaIn.getId(), operaIn.getTextName(), ++size, p, 0,true));
                one = true;
            }else {
                data.add(buildOperaItem(operaIn.getId(), operaIn.getTextName(), ++size, p, 1,false));
            }
        }
        if(one){
            data.add(buildOperaItem(SneakLifeSystemEnum.OPERA_IN.toName(), SneakLifeSystemEnum.OPERA_IN_VALUE.toName(), z, pid, 0,true));
        }else if (operaInList.size() > 0){
            data.add(buildOperaItem(SneakLifeSystemEnum.OPERA_IN.toName(), SneakLifeSystemEnum.OPERA_IN_VALUE.toName(), z, pid, 1,false));
        }
    }

    /**
     * Build function query
     * @param map Option data
     * @param pid View display pid
     * @param roleFunctionMenuId Roles already have function menus
     */
    private void buildOperaBoTree(Map<String, Object> map, int pid, List<String> roleFunctionMenuId){
        int z = ++size;
        int p = size;
        boolean one = false;
        List<OperaBo> operaBoList = operaBoMapper.findOperaBo(map);
        for (OperaBo operaBo : operaBoList) {
            if(roleFunctionMenuId.contains(operaBo.getId())){
                data.add(buildOperaItem(operaBo.getId(), operaBo.getTextName(), ++size, p, 0,true));
                one = true;
            }else {
                data.add(buildOperaItem(operaBo.getId(), operaBo.getTextName(), ++size, p, 1,false));
            }
        }
        if(one){
            data.add(buildOperaItem(SneakLifeSystemEnum.OPERA_BO.toName(), SneakLifeSystemEnum.OPERA_BO_VALUE.toName(), z, pid, 0,true));
        }else if(operaBoList.size() > 0){
            data.add(buildOperaItem(SneakLifeSystemEnum.OPERA_BO.toName(), SneakLifeSystemEnum.OPERA_BO_VALUE.toName(), z, pid, 1,false));
        }
    }

    @Override
    @LogicalLogAn
    public List<Map<String, Object>> getSelectsKyByMenuId(String menuId, String htmlType) {
        return operaInMapper.getSelectsKyByMenuId(menuId, htmlType);
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

    /**
     * Process the display of function input at the front end
     * @param list List to display
     * @param gn Group number
     * @param t Whether to return directly
     * @return Function input options displayed on the front end
     */
    private <E> List<List<E>> displayOpera(List<E> list, int gn, boolean t){
        List<List<E>> data = new LinkedList<>();
        if(t){
            data.add(list);
            return data;
        }
        // The processing measure is 2
        List<E> temp = new ArrayList<>();
        boolean odd = list.size() % gn != 0;
        for (int i = 1; i <= list.size(); i++) {
            E operaIn = list.get(i - 1);
            if(i % gn != 0){
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
}
