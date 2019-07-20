package com.sneaklife.service.system.authority.imp;

import com.sneaklife.common.CommonUtil;
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

@Service
@SuppressWarnings("unchecked")
public class FunctionConfigServiceImp implements FunctionConfigService {

    @Autowired
    private SystemMenuJpa systemMenuJpa;

    @Autowired
    private OperaService operaService;

    @Override
    public void insertFunctionConfig(Map<String, Object> map) throws Exception {
        String id = String.valueOf(map.get("id"));
        if("opera-columns".equals(id) || "opera-in".equals(id)){
            throw new SneakLifeException(CommonUtil.respResult(RespCode.MSG_NO_OPERA_ADD.toValue(),RespCode.MSG_NO_OPERA_ADD.toMsg()));
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
     * 查找子节点
     * @param parent 父节点
     * @param list 所有节点
     * @return 父节点带子节点
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
     * 从所有节点中删除重复的节点
     * @param parentMenu 删除项
     * @param list 所有节点
     * @param size 所有节点的大小，可以改变list长度，不需要则传0
     * @return 所有节点剩余大小
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
