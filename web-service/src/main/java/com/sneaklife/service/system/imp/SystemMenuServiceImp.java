package com.sneaklife.service.system.imp;

import com.sneaklife.common.CommonUtil;
import com.sneaklife.dao.entity.SystemMenu;
import com.sneaklife.service.system.SystemMenuService;
import com.sneaklife.dao.system.SystemMenuJpa;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 */
@Service
public class SystemMenuServiceImp implements SystemMenuService {

    @Autowired
    private SystemMenuJpa systemMenuJpa;

    private static Logger log = LoggerFactory.getLogger(SystemMenuServiceImp.class);

    @Override
    public ResponseEntity<String> getMenu() {
        List<SystemMenu> data = new ArrayList<>();
        List<SystemMenu> list = systemMenuJpa.findAll();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            SystemMenu systemMenu = list.get(i);
            SystemMenu parentMenu = findChildMenu(systemMenu, list);
            size = removeNode(parentMenu, list, size);
            data.add(parentMenu);
        }
        log.info("返回数据：【{}】", data);
        return CommonUtil.respResultDataSUCCEED(data);
    }

    /**
     * Find child node
     * @param parent The parent node
     * @param list All the nodes
     * @return Parent node tape node
     */
    private SystemMenu findChildMenu(SystemMenu parent, List<SystemMenu> list){
        List<SystemMenu> childMenu = new ArrayList<>();
        for (SystemMenu menu : list) {
            if (parent.getId().equals(menu.getPid())) {
                SystemMenu child = findChildMenu(menu, list);
                childMenu.add(child);
                parent.setSon(childMenu);
            }
        }
        return parent;
    }

    /**
     * Remove duplicate nodes from all nodes
     * @param parentMenu Delete the item
     * @param list All the nodes
     * @param size The size of all nodes, can change the list length, do not need to pass 0
     * @return Residual size of all nodes
     */
    private int removeNode(SystemMenu parentMenu, List<SystemMenu> list, int size){
        List<SystemMenu> childMenu = parentMenu.getSon();
        for (SystemMenu child : childMenu) {
            Iterator<SystemMenu> it = list.iterator();
            while (it.hasNext()) {
                SystemMenu menu = it.next();
                if (child.getId().equals(menu.getId())) {
                    it.remove();
                    size--;
                }
            }
            size = removeNode(child, list, size);
        }
        return size;
    }
}
