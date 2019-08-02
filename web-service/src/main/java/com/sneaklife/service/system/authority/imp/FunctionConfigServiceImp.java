package com.sneaklife.service.system.authority.imp;

import com.sneaklife.common.CommonUtil;
import com.sneaklife.dao.entity.OperaSb;
import com.sneaklife.dao.entity.SystemMenu;
import com.sneaklife.dao.entity.modal.TableOpera;
import com.sneaklife.dao.system.SystemMenuJpa;
import com.sneaklife.exception.SneakLifeException;
import com.sneaklife.resp.RespCode;
import com.sneaklife.service.system.OperaService;
import com.sneaklife.service.system.authority.FunctionConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
/**
 * @author https://github.com/XiFYuW
 */
@Service
@SuppressWarnings("unchecked")
public class FunctionConfigServiceImp implements FunctionConfigService {

    @Autowired
    private SystemMenuJpa systemMenuJpa;

    @Autowired
    private OperaService operaService;

    @Override
    public void deleteFunctionConfig(Map<String, Object> map) throws Exception {
        operaService.deleteOpera(map);
    }

    @Override
    public void updateFunctionConfig(Map<String, Object> map) throws Exception {
        operaService.updateOpera(map);
    }

    @Override
    public void insertFunctionConfig(Map<String, Object> map) throws Exception {
        String id = String.valueOf(map.get("id"));
        if("opera-columns".equals(id) || "opera-in".equals(id)){
            throw new SneakLifeException(CommonUtil.respResult(RespCode.MSG_NO_OPERA_ADD.toValue(),RespCode.MSG_NO_OPERA_ADD.toMsg()));
        }

        if("opera-sb".equals(id)){
            OperaSb operaSb = new OperaSb();
            operaSb.setCode("3");
            operaSb.setIcon("glyphicon-pencil");
            operaSb.setMenuId(String.valueOf(map.get("tempMenuId")));
            operaSb.setText(String.valueOf(map.get("pid")));
            operaSb.setType("button");
            operaSb.setUrl("#");
            operaService.insertOperaSb(operaSb);
        }

        throw new SneakLifeException(CommonUtil.respResultTJCG());
    }

    @Override
    public ResponseEntity<String> functionConfig(Map<String, Object> map){
        List<Map<String,Object>> data = new ArrayList<>();
        List<SystemMenu> list = systemMenuJpa.findAll();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            SystemMenu systemMenu = list.get(i);
            Map<String,Object> parentMenu = findChildMenu(systemMenu, list);
            size = removeNode(parentMenu, list, size);
            data.add(parentMenu);
        }
        Map<String,Object> systemSetting = new HashMap<>();
        systemSetting.put("text", "SystemSetting");
        systemSetting.put("url", "#");
        systemSetting.put("nodes", data);
        List<Map<String,Object>> left = new ArrayList<>();
        left.add(systemSetting);
        return CommonUtil.respResultDataSUCCEED(left);
    }

    @Override
    public ResponseEntity<String> functionConfigTreeView(Map<String, Object> map) {
        map.put("isShow",0);
        TableOpera tableOpera = operaService.buildOperaBody(map,false);
        return CommonUtil.respResultDataSUCCEED(tableOpera);
    }

    @Override
    public ResponseEntity<String> getFunctionConfig(Map<String, Object> map) {
        List<Map<String,Object>> data = operaService.buildOperaTreeGrid(map);
        operaService.clean();
        return CommonUtil.respResultDataSUCCEED(data);
    }

    /**
     * Find child node
     * @param parent The parent node
     * @param list All the nodes
     * @return Parent node tape node
     */
    private Map<String,Object> findChildMenu(SystemMenu parent, List<SystemMenu> list){
        Map<String,Object> parentMap = new HashMap<>();
        parentMap.put("text", parent.getTab());
        parentMap.put("url", parent.getItemUrl());
        parentMap.put("id", parent.getId());
        parentMap.put("nodes", null);
        List<Map<String,Object>> childList = new ArrayList<>();
        for (SystemMenu menu : list) {
            if (parent.getId().equals(menu.getPid())) {
                Map<String,Object> child = findChildMenu(menu, list);
                childList.add(child);
                parentMap.put("nodes", childList);
            }
        }
        return parentMap;
    }

    /**
     * Remove duplicate nodes from all nodes
     * @param parentMenu Delete the item
     * @param list All the nodes
     * @param size The size of all nodes, can change the list length, do not need to pass 0
     * @return Residual size of all nodes
     */
    private int removeNode(Map<String,Object> parentMenu, List<SystemMenu> list, int size){
        List<Map<String,Object>> childMenu = (List<Map<String,Object>>)parentMenu.get("nodes");
        if(!CommonUtil.isNull(childMenu)){
            parentMenu.remove("nodes");
            return size;
        }
        for (Map<String, Object> child : childMenu) {
            Iterator<SystemMenu> it = list.iterator();
            while (it.hasNext()) {
                SystemMenu menu = it.next();
                if (child.get("id").equals(menu.getId())) {
                    it.remove();
                    size--;
                }
            }
            size = removeNode(child, list, size);
        }
        return size;
    }
}
