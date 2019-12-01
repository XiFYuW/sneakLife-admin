package com.sneaklife.pms.service.system.menu.imp;

import com.sneaklife.pms.dao.system.SystemMenuJpa;
import com.sneaklife.pms.dao.system.SystemMenuMapper;
import com.sneaklife.pms.entity.SystemMenu;
import com.sneaklife.pms.entity.modal.TableOpera;
import com.sneaklife.pms.service.common.CommonService;
import com.sneaklife.pms.service.common.OperaService;
import com.sneaklife.pms.service.common.SelectTreeViewService;
import com.sneaklife.pms.service.system.menu.SystemMenuService;
import com.sneaklife.ut.interfaces.Nodes;
import com.sneaklife.ut.iws.IwsContext;
import com.sneaklife.ut.page.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 */
@Service
public class SystemMenuServiceImp extends CommonService implements SystemMenuService,
        Nodes<SystemMenu, SystemMenu, List<SystemMenu>> {

    @Autowired
    private SystemMenuJpa systemMenuJpa;

    @Autowired
    private SystemMenuMapper systemMenuMapper;

    @Autowired
    private OperaService operaService;

    @Autowired
    private Nodes<SystemMenu, SystemMenu, List<SystemMenu>> nodes;

    @Autowired
    private SelectTreeViewService selectTreeViewService;

    private static Logger log = LoggerFactory.getLogger(SystemMenuServiceImp.class);

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<String> getMenu() {
        List<SystemMenu> data = new ArrayList<>();
        List<SystemMenu> list = systemMenuMapper.getByIsDel(0);
        int size = list.size();
        for(int i = 0; i < size; i++){
            SystemMenu systemMenu = list.get(i);
            SystemMenu parentMenu = nodes.findChildNode(systemMenu, list, true);
            size = nodes.removeChildNode(parentMenu, list, size);
            data.add(parentMenu);
        }
        return IwsContext.respResultBodyToSC(data);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<String> getSystemFunctionMenu(Map<String, Object> map, PageInfo pageInfo) throws Exception {
        Page<Map<String, Object>> page = systemMenuJpa.findAllPage(getPageable(pageInfo));
        return IwsContext.respResultBodyToSC(pageToMap(page));
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<String> systemFunctionMenu(Map<String, Object> map) throws Exception {
        map.put("isShow", 0);
        TableOpera tableOpera = operaService.buildOperaBody(map, false);
        return IwsContext.respResultBodyToSC(tableOpera);
    }

    @Override
    public int removeChildNode(SystemMenu parentMenu, List<SystemMenu> list, int size){
        List<SystemMenu> childMenu = parentMenu.getSon();
        if(!IwsContext.isNotNull(childMenu)){
            return operaService.removeNode(parentMenu, list, size);
        }
        for(SystemMenu child : childMenu){
            Iterator<SystemMenu> it = list.iterator();
            while(it.hasNext()){
                SystemMenu menu = it.next();
                boolean isRemove = child.getId().equals(menu.getId()) || parentMenu.getId().equals(menu.getId());
                if(isRemove){
                    it.remove();
                    size--;
                }
            }
            size = removeChildNode(child, list, size);
        }
        return size;
    }

    @Override
    public SystemMenu findChildNode(SystemMenu node, List<SystemMenu> list, boolean parent) {
        List<SystemMenu> childMenu = new ArrayList<>();
        for(SystemMenu menu : list){
            if(node.getId().equals(menu.getPid())){
                SystemMenu child = findChildNode(menu, list, false);
                childMenu.add(child);
                node.setSon(childMenu);
            }
            if(parent && node.getPid().equals(menu.getId())){
                return findChildNode(menu, list, false);
            }
        }
        return node;
    }

    @Override
    @Transactional
    public void insertSystemFunctionMenu(Map<String, Object> map) throws Exception {
        insert(systemMenuMapper,map);
    }

    @Override
    @Transactional
    public void updateSystemFunctionMenu(Map<String, Object> map) throws Exception {
        update(systemMenuMapper,map);
    }

    @Override
    @Transactional
    public void deleteSystemFunctionMenu(Map<String, Object> map) throws Exception {
        delete(systemMenuMapper,map);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<String> selectTreeView(Map<String, Object> map) {
        return selectTreeViewService.selectTreeView(map);
    }
}
