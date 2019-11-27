package com.sneaklife.pms.service.system.menu.imp;

import com.sneaklife.pms.dao.system.SystemMenuJpa;
import com.sneaklife.pms.dao.system.SystemMenuMapper;
import com.sneaklife.pms.entity.SystemMenu;
import com.sneaklife.pms.service.system.menu.SystemMenuService;
import com.sneaklife.ut.interfaces.Nodes;
import com.sneaklife.ut.iws.IwsContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author https://github.com/XiFYuW
 */
@Service
public class SystemMenuServiceImp implements SystemMenuService, Nodes<SystemMenu, SystemMenu, List<SystemMenu>> {

    @Autowired
    private SystemMenuJpa systemMenuJpa;

    @Autowired
    private SystemMenuMapper systemMenuMapper;

    @Autowired
    private Nodes<SystemMenu, SystemMenu, List<SystemMenu>> nodes;

    private static Logger log = LoggerFactory.getLogger(SystemMenuServiceImp.class);

    @Override
    public ResponseEntity<String> getMenu() {
        List<SystemMenu> data = new ArrayList<>();
        List<SystemMenu> list = systemMenuMapper.getByIsDel(0);
        int size = list.size();
        for(int i = 0; i < size; i++){
            SystemMenu systemMenu = list.get(i);
            SystemMenu parentMenu = nodes.findChildNode(systemMenu, list);
            size = nodes.removeNode(parentMenu, list, size);
            nodes.removeNode(parentMenu, data, data.size());
            data.add(parentMenu);
        }
        return IwsContext.respResultBodyToSC(data);
    }

    @Override
    public SystemMenu findChildNode(SystemMenu parent, List<SystemMenu> list){
        List<SystemMenu> childMenu = new ArrayList<>();
        for(SystemMenu menu : list){
            // 判断是不是满仔
            if(parent.getId().equals(menu.getPid())){
                SystemMenu child = findChildNode(menu, list);
                childMenu.add(child);
                parent.setSon(childMenu);
            }
        }
        return parent;
    }

    @Override
    public int removeNode(SystemMenu parentMenu, List<SystemMenu> list, int size){
        List<SystemMenu> childMenu = parentMenu.getSon();
        for(SystemMenu child : childMenu){
            Iterator<SystemMenu> it = list.iterator();
            while(it.hasNext()){
                SystemMenu menu = it.next();
                if(child.getId().equals(menu.getId())){
                    it.remove();
                    size--;
                }
            }
            size = removeNode(child, list, size);
        }
        return size;
    }

    @Override
    public SystemMenu findNode(SystemMenu node, List<SystemMenu> list, boolean parent) {
        return null;
    }
}
