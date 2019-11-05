package com.sneaklife.pms.service.system.imp;

import com.sneaklife.dao.entity.SystemMenu;
import com.sneaklife.dao.system.SystemMenuJpa;
import com.sneaklife.dao.system.SystemMenuMapper;
import com.sneaklife.pms.service.system.SystemMenuService;
import com.sneaklife.util.common.CommonUtil;
import com.sneaklife.util.interfaces.Nodes;
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

    private static Logger log = LoggerFactory.getLogger(SystemMenuServiceImp.class);

    @Override
    public ResponseEntity<String> getMenu() {
        List<SystemMenu> data = new ArrayList<>();
        List<SystemMenu> list = systemMenuMapper.getByIsDel(0);
        int size = list.size();
        for (int i = 0; i < size; i++) {
            SystemMenu systemMenu = list.get(i);
            SystemMenu parentMenu = findChildMenu(systemMenu, list);
            size = removeNode(parentMenu, list, size);
            data.add(parentMenu);
        }
        return CommonUtil.respResultDataSUCCEED(data);
    }

    @Override
    public SystemMenu findChildMenu(SystemMenu parent, List<SystemMenu> list){
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

    @Override
    public int removeNode(SystemMenu parentMenu, List<SystemMenu> list, int size){
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
