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
        log.info("返回数据：{}", data);
        return CommonUtil.respResultDataSUCCEED(data);
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
