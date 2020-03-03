package com.sneaklife.pms.service.common.imp;

import com.sneaklife.pms.service.common.RedisService;
import com.sneaklife.pms.service.common.SelectTreeService;
import com.sneaklife.ut.iws.IwsContext;
import com.sneaklife.ut.log.LogicalLogAn;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/2/6 16:18
 */
@Service
public class RightSelectTreeServiceImp implements SelectTreeService {

    private final SelectTreeService leftSelectTreeServiceImp;

    private final RedisService redisService;

    public RightSelectTreeServiceImp(SelectTreeService leftSelectTreeServiceImp, RedisService redisService) {
        this.leftSelectTreeServiceImp = leftSelectTreeServiceImp;
        this.redisService = redisService;
    }

    @Override
    @LogicalLogAn
    public List<Map<String, Object>> selectTree(Map<String, Object> map) throws Exception {
        List<Map<String,Object>> data = leftSelectTreeServiceImp.selectTree(map);
        List<String> operaList = redisService.getOpera(map);
        return peek(data, operaList);
    }

    private List<Map<String,Object>> peek(List<Map<String,Object>> data, List<String> operaList){
        return data.stream().peek(map1 -> setChecked(map1, operaList)).collect(Collectors.toList());
    }

    @SuppressWarnings("unchecked")
    private void setChecked(Map<String,Object> map1, List<String> operaList){
        String id = String.valueOf(map1.get("id"));
        if(operaList.contains(id)){
            map1.put("state", getMap(true));
        }else {
            map1.put("state", getMap(false));
        }
        List<Map<String,Object>> childMenu = (List<Map<String,Object>>) map1.get("nodes");
        if(IwsContext.isNotNull(childMenu)){
            peek(childMenu, operaList);
        }
    }

    private Map<String,Boolean> getMap(boolean isState){
        Map<String,Boolean> map = new HashMap<>();
        map.put("checked", isState);
        return map;
    }
}
