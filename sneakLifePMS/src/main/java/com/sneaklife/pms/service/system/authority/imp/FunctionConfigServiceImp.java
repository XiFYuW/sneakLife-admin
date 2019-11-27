package com.sneaklife.pms.service.system.authority.imp;

import com.sneaklife.pms.dao.system.SystemMenuJpa;
import com.sneaklife.pms.dao.system.SystemMenuMapper;
import com.sneaklife.pms.entity.SystemMenu;
import com.sneaklife.pms.entity.modal.TableOpera;
import com.sneaklife.pms.service.common.OperaService;
import com.sneaklife.pms.service.system.authority.FunctionConfigService;
import com.sneaklife.ut.exception.SneakLifeException;
import com.sneaklife.ut.iws.IwsContext;
import com.sneaklife.ut.interfaces.Interceptor.ChildNode;
import com.sneaklife.ut.interfaces.Nodes;
import com.sneaklife.ut.interfaces.ParameterTransformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author https://github.com/XiFYuW
 */
@Service
@SuppressWarnings("unchecked")
public class FunctionConfigServiceImp implements FunctionConfigService,
        ParameterTransformation<SystemMenu, Map<String,Object>, List<Map<String,Object>>>,
        Nodes<SystemMenu, Map<String,Object>, List<SystemMenu>> {

    @Autowired
    private SystemMenuJpa systemMenuJpa;

    @Autowired
    private SystemMenuMapper systemMenuMapper;

    @Autowired
    private OperaService operaService;

    @Autowired
    private ParameterTransformation<SystemMenu, Map<String,Object>, List<Map<String,Object>>> ptf;

    @Autowired
    private Nodes<SystemMenu, Map<String,Object>, List<SystemMenu>> nodes;

    @Override
    public void deleteFunctionConfig(Map<String, Object> map) throws Exception {
        throw new SneakLifeException(IwsContext.respResultSCCG());
    }

    @Override
    public void updateFunctionConfig(Map<String, Object> map) throws Exception {
        throw new SneakLifeException(IwsContext.respResultXGCG());
    }

    @Override
    public void insertFunctionConfig(Map<String, Object> map) throws Exception {
        throw new SneakLifeException(IwsContext.respResultTJCG());
    }

    @Override
    public ResponseEntity<String> functionConfig(Map<String, Object> map){
        List<Map<String,Object>> data = new ArrayList<>();
        List<SystemMenu> list = systemMenuMapper.getByIsDel(0);
        String menuId = String.valueOf(map.get("menuId"));
        String itemUrl = systemMenuMapper.getItemUrlById(menuId);
        int size = list.size();
        for (int i = 0; i < size; i++) {
            SystemMenu systemMenu = list.get(i);
            systemMenu.setItemUrl(itemUrl);
            Map<String,Object> parentMenu = nodes.findNode(systemMenu, list, true);
            size = nodes.removeNode(parentMenu, list, size);
            i--;
            data.add(parentMenu);
        }
        return IwsContext.respResultBodyToSC(data);
    }

    @Override
    public ResponseEntity<String> functionConfigTreeView(Map<String, Object> map) {
        map.put("isShow",0);
        TableOpera tableOpera = operaService.buildOperaBody(map,false);
        return IwsContext.respResultBodyToSC(tableOpera);
    }

    @Override
    public ResponseEntity<String> getFunctionConfig(Map<String, Object> map) {
        List<Map<String,Object>> data = operaService.buildOperaTreeGrid(map);
        operaService.clean();
        return IwsContext.respResultBodyToSC(data);
    }

    @ChildNode
    @Override
    public Map<String,Object> findNode(SystemMenu node, List<SystemMenu> list, boolean parent){
        Map<String,Object> parentMap = ptf.paramTrans(new HashMap<>(), node);
        List<Map<String,Object>> childList = new ArrayList<>();
        for (SystemMenu menu : list) {
            menu.setItemUrl(node.getItemUrl());
            if (node.getId().equals(menu.getPid())) {
                Map<String,Object> child = findNode(menu, list, false);
                childList.add(child);
                parentMap.put("nodes", childList);
            }

            if(parent && node.getPid().equals(menu.getId())){
                return findNode(menu, list, false);
            }
        }
        return parentMap;
    }

    @Override
    public int removeNode(Map<String,Object> parentMenu, List<SystemMenu> list, int size){
        List<Map<String,Object>> childMenu = (List<Map<String,Object>>)parentMenu.get("nodes");
        if(!IwsContext.isNull(childMenu)){
            parentMenu.remove("nodes");
            Iterator<SystemMenu> it = list.iterator();
            while (it.hasNext()) {
                SystemMenu menu = it.next();
                boolean isRemove = parentMenu.get("id").equals(menu.getId());
                if (isRemove) {
                    it.remove();
                    size--;
                }
            }
            return size;
        }

        for (Map<String, Object> child : childMenu) {
            Iterator<SystemMenu> it = list.iterator();
            while (it.hasNext()) {
                SystemMenu menu = it.next();
                boolean isRemove = child.get("id").equals(menu.getId()) || parentMenu.get("id").equals(menu.getId());
                if (isRemove) {
                    it.remove();
                    size--;
                }
            }
            size = removeNode(child, list, size);
        }
        return size;
    }

    @Override
    public Map<String, Object> paramTrans(Map<String, Object> map, SystemMenu systemMenu) {
        map.put("text", systemMenu.getTab());
        map.put("url", systemMenu.getItemUrl());
        map.put("id", systemMenu.getId());
        map.put("nodes", null);
        return map;
    }

    @Override
    public List<Map<String, Object>> fixedParamTrans(List<Map<String, Object>> list, Map<String, Object> map) {
        List<Map<String, Object>> data = new ArrayList<>();
        map.put("text", "SystemSetting");
        map.put("url", "#");
        map.put("nodes", list);
        data.add(map);
        return data;
    }

    @Override
    public Map<String, Object> findChildNode(SystemMenu parent, List<SystemMenu> list) {
        return null;
    }
}
