package com.sneaklife.service.system.imp;

import com.sneaklife.dao.system.entity.SystemMenu;
import com.sneaklife.service.system.SystemMenuService;
import com.sneaklife.dao.system.dao.SystemMenuDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class SystemMenuServiceImp implements SystemMenuService {
    @Autowired
    private SystemMenuDao systemMenuDao;
    @Override
    public List<SystemMenu> getMenu() {
        List<SystemMenu> returnData = new ArrayList<>();
        List<SystemMenu> list = systemMenuDao.findAll();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            SystemMenu systemMenu = list.get(i);
            SystemMenu parentMenu = findChildMenu(systemMenu, list);
            removeNode(parentMenu, returnData, 0);
            size = removeNode(parentMenu, list, size);
            returnData.add(parentMenu);
        }
        return returnData;
    }

    /**
     * 查找子节点
     * @param parent 父节点
     * @param list 所有节点
     * @return 父节点带子节点
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
     * 从所有节点中删除重复的节点
     * @param parentMenu 删除项
     * @param list 所有节点
     * @param size 所有节点的大小，可以改变list长度，不需要则传0
     * @return 所有节点剩余大小
     */
    private int removeNode(SystemMenu parentMenu, List<SystemMenu> list, int size){
        List<SystemMenu> childMenu = parentMenu.getSon();
        for (int i = 0; i < childMenu.size(); i++) {
            SystemMenu child = childMenu.get(i);
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
}
