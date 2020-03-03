package com.sneaklife.pms.service.common.imp;

import com.sneaklife.pms.dao.system.SystemMenuMapper;
import com.sneaklife.pms.service.common.SelectTreeService;
import com.sneaklife.ut.interfaces.Nodes;
import com.sneaklife.ut.interfaces.ParameterTransformation;
import com.sneaklife.ut.iws.IwsContext;
import com.sneaklife.ut.log.LogicalLogAn;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/11/28 12:15
 */
@Service
public class LeftSelectTreeServiceImp implements SelectTreeService,
        ParameterTransformation<Map<String,Object>, Map<String,Object>, List<Map<String,Object>>>,
        Nodes<Map<String,Object>, Map<String,Object>, List<Map<String,Object>>> {

    @Resource
    private SystemMenuMapper systemMenuMapper;

    @Resource(name = "leftSelectTreeServiceImp")
    private ParameterTransformation<Map<String,Object>, Map<String,Object>, List<Map<String,Object>>> ptf;

    @Resource(name = "leftSelectTreeServiceImp")
    private Nodes<Map<String,Object>, Map<String,Object>, List<Map<String,Object>>> nodes;

    @Override
    @LogicalLogAn
    public List<Map<String,Object>> selectTree(Map<String, Object> map) {
        List<Map<String,Object>> list = systemMenuMapper.getByIsDel(0);
        String menuId = String.valueOf(map.get("menuId"));
        String itemUrl = systemMenuMapper.getItemUrlById(menuId);
        return list.stream().map(x ->  {
            x.put("itemUrl", itemUrl);
            Map<String,Object> m = nodes.findChildNode(x, list, true);
            if(!IwsContext.isNotNull(m.get("nodes"))){
                m.remove("nodes");
            }
            return m;
        }).filter(distinctByKey(x -> x.get("id"))).collect(Collectors.toList());
    }

    @Override
    public Map<String,Object> findChildNode(Map<String,Object> node, List<Map<String,Object>> list, boolean parent){
        Map<String,Object> parentMap = ptf.paramTrans(new HashMap<>(), node);
        List<Map<String,Object>> childList = new ArrayList<>();
        for (Map<String,Object> menu : list) {
            menu.put("itemUrl", node.get("itemUrl"));
            if (node.get("id").equals(menu.get("pid"))) {
                Map<String,Object> child = findChildNode(menu, list, false);
                childList.add(child);
                parentMap.put("nodes", childList);
            }

            if(parent && node.get("pid").equals(menu.get("id"))){
                return findChildNode(menu, list, false);
            }
        }
        return parentMap;
    }

    @Override
    public Map<String, Object> paramTrans(Map<String, Object> map, Map<String,Object> systemMenu) {
        map.put("text", systemMenu.get("tab"));
        map.put("url", systemMenu.get("itemUrl"));
        map.put("id", systemMenu.get("id"));
        map.put("nodes", null);
        return map;
    }

    @Override
    public List<Map<String, Object>> fixedParamTrans(List<Map<String, Object>> list, Map<String, Object> map) {
        return null;
    }
}
