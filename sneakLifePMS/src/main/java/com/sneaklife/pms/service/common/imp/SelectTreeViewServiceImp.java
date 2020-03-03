package com.sneaklife.pms.service.common.imp;

import com.sneaklife.pms.service.common.OperaService;
import com.sneaklife.pms.service.common.SelectTreeViewService;
import com.sneaklife.ut.log.LogicalLogAn;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/12/1 12:28
 */
@Service
public class SelectTreeViewServiceImp extends LeftSelectTreeServiceImp implements SelectTreeViewService {

    private final OperaService operaService;

    public SelectTreeViewServiceImp(OperaService operaService) {
        this.operaService = operaService;
    }

    @Override
    @LogicalLogAn
    public Map<String, Object> selectTreeView(Map<String, Object> map) {
        String express = String.valueOf(map.get("express"));
        String menuId = String.valueOf(map.get("menuId"));
        List<Map<String, Object>> selectKey = operaService.getSelectsKyByMenuId(menuId, "selectsTree");
        List<Map<String,Object>> object = super.selectTree(map);
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
    public Map<String, Object> paramTrans(Map<String, Object> map, Map<String, Object> systemMenu) {
        map.put("text", systemMenu.get("tab"));
        map.put("value", systemMenu.get("id"));
        map.put("id", systemMenu.get("id"));
        map.put("nodes", null);
        return map;
    }

    @Override
    public List<Map<String, Object>> fixedParamTrans(List<Map<String, Object>> list, Map<String, Object> map) {
        return super.fixedParamTrans(list, map);
    }
}
