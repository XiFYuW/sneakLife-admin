package com.sneaklife.pms.service.system.authority.imp;

import com.sneaklife.dao.entity.OperaSb;
import com.sneaklife.dao.entity.SystemMenu;
import com.sneaklife.dao.entity.modal.TableOpera;
import com.sneaklife.dao.system.SystemMenuJpa;
import com.sneaklife.dao.system.SystemMenuMapper;
import com.sneaklife.pms.service.system.OperaService;
import com.sneaklife.pms.service.system.authority.FunctionConfigService;
import com.sneaklife.util.code.exception.SneakLifeException;
import com.sneaklife.util.code.resp.RespCode;
import com.sneaklife.util.common.CommonUtil;
import com.sneaklife.util.interfaces.Interceptor.ChildNode;
import com.sneaklife.util.interfaces.Nodes;
import com.sneaklife.util.interfaces.ParameterTransformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    @Resource(name = "functionConfigServiceImp")
    private Nodes<SystemMenu, Map<String,Object>, List<SystemMenu>> nodes;

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
        List<SystemMenu> list = systemMenuMapper.getByIsDel(0);
        String menuId = String.valueOf(map.get("menuId"));
        String itemUrl = systemMenuMapper.getItemUrlById(menuId);
        int size = list.size();
        for (int i = 0; i < size; i++) {
            SystemMenu systemMenu = list.get(i);
            systemMenu.setItemUrl(itemUrl);
            Map<String,Object> parentMenu = nodes.findChildMenu(systemMenu, list);
            size = nodes.removeNode(parentMenu, list, size);
            data.add(parentMenu);
        }
        List<Map<String, Object>> left = fixedParamTrans(data, new HashMap<>());
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

    @ChildNode
    @Override
    public Map<String,Object> findChildMenu(SystemMenu parent, List<SystemMenu> list){
        Map<String,Object> parentMap = paramTrans(new HashMap<>(), parent);
        List<Map<String,Object>> childList = new ArrayList<>();
        for (SystemMenu menu : list) {
            menu.setItemUrl(parent.getItemUrl());
            if (parent.getId().equals(menu.getPid())) {
                Map<String,Object> child = findChildMenu(menu, list);
                childList.add(child);
                parentMap.put("nodes", childList);
            }
        }
        return parentMap;
    }

    @Override
    public int removeNode(Map<String,Object> parentMenu, List<SystemMenu> list, int size){
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
}
