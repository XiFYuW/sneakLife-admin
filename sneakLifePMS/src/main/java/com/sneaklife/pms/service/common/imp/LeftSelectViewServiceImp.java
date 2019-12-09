package com.sneaklife.pms.service.common.imp;

import com.sneaklife.pms.dao.system.SystemMenuMapper;
import com.sneaklife.pms.entity.SystemMenu;
import com.sneaklife.pms.service.common.LeftSelectViewService;
import com.sneaklife.ut.interfaces.Interceptor.ChildNode;
import com.sneaklife.ut.interfaces.Nodes;
import com.sneaklife.ut.interfaces.ParameterTransformation;
import com.sneaklife.ut.iws.IwsContext;
import com.sneaklife.ut.log.SneakLifeAnLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/11/28 12:15
 */
@Service
public class LeftSelectViewServiceImp implements LeftSelectViewService,
        ParameterTransformation<SystemMenu, Map<String,Object>, List<Map<String,Object>>>,
        Nodes<SystemMenu, Map<String,Object>, List<SystemMenu>> {

    @Autowired
    private SystemMenuMapper systemMenuMapper;

    @Autowired
    private ParameterTransformation<SystemMenu, Map<String,Object>, List<Map<String,Object>>> ptf;

    @Autowired
    private Nodes<SystemMenu, Map<String,Object>, List<SystemMenu>> nodes;

    @Override
    @SneakLifeAnLog
    public List<Map<String,Object>> leftSelectsView(Map<String, Object> map) {
        List<Map<String,Object>> data = new ArrayList<>();
        List<SystemMenu> list = systemMenuMapper.getByIsDel(0);
        String menuId = String.valueOf(map.get("menuId"));
        String itemUrl = systemMenuMapper.getItemUrlById(menuId);
        int size = list.size();
        for (int i = 0; i < size; i++) {
            SystemMenu systemMenu = list.get(i);
            systemMenu.setItemUrl(itemUrl);
            Map<String,Object> parentMenu = nodes.findChildNode(systemMenu, list, true);
            size = nodes.removeChildNode(parentMenu, list, size);
            i--;
            data.add(parentMenu);
        }
        return data;
    }

    @ChildNode
    @Override
    public Map<String,Object> findChildNode(SystemMenu node, List<SystemMenu> list, boolean parent){
        Map<String,Object> parentMap = ptf.paramTrans(new HashMap<>(), node);
        List<Map<String,Object>> childList = new ArrayList<>();
        for (SystemMenu menu : list) {
            menu.setItemUrl(node.getItemUrl());
            if (node.getId().equals(menu.getPid())) {
                Map<String,Object> child = findChildNode(menu, list, false);
                childList.add(child);
                parentMap.put("nodes", childList);
            }

            if(parent && node.getPid().equals(menu.getId())){
                return findChildNode(menu, list, false);
            }
        }
        return parentMap;
    }

    @Override
    public int removeChildNode(Map<String,Object> parentMenu, List<SystemMenu> list, int size){
        List<Map<String,Object>> childMenu = (List<Map<String,Object>>)parentMenu.get("nodes");
        if(!IwsContext.isNotNull(childMenu)){
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
            size = removeChildNode(child, list, size);
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
        return null;
    }
}
