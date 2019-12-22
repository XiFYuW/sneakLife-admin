package com.sneaklife.pms.service.common.imp;

import com.sneaklife.pms.dao.system.SystemMenuMapper;
import com.sneaklife.pms.entity.SystemMenu;
import com.sneaklife.pms.service.common.OperaService;
import com.sneaklife.pms.service.common.SelectTreeViewService;
import com.sneaklife.ut.interfaces.Nodes;
import com.sneaklife.ut.interfaces.ParameterTransformation;
import com.sneaklife.ut.log.SneakLifeAnLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/12/1 12:28
 */
@Service
public class SelectTreeViewServiceImp extends LeftSelectViewServiceImp implements SelectTreeViewService {

    @Resource
    private OperaService operaService;

    @Override
    @SneakLifeAnLog
    public Map<String, Object> selectTreeView(Map<String, Object> map) {
        String express = String.valueOf(map.get("express"));
        String menuId = String.valueOf(map.get("menuId"));
        List<Map<String, Object>> selectKey = operaService.getSelectsKyByMenuId(menuId, "selectsTree");
        List<Map<String,Object>> object = super.leftSelectsView(map);
        map.clear();
        String[] types = express.split(",");
        for (String type : types) {
            String[] match = type.split(":");
            for (Map<String, Object> select : selectKey) {
                if (match[0].equals(String.valueOf(select.get("id")))) {
                    String field = String.valueOf(select.get("field"));
                    map.put(field, object);
                }
            }
        }
        return map;
    }

    @Override
    public Map<String, Object> paramTrans(Map<String, Object> map, SystemMenu systemMenu) {
        map.put("text", systemMenu.getTab());
        map.put("value", systemMenu.getId());
        map.put("id", systemMenu.getId());
        map.put("nodes", null);
        return map;
    }

    @Override
    public List<Map<String, Object>> fixedParamTrans(List<Map<String, Object>> list, Map<String, Object> map) {
        return super.fixedParamTrans(list, map);
    }
}
