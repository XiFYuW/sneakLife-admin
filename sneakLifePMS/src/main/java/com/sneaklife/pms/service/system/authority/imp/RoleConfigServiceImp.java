package com.sneaklife.pms.service.system.authority.imp;

import com.sneaklife.config.cache.SneakLifeAuthorityManagementCacheEvict;
import com.sneaklife.pms.dao.system.authority.roleConfig.RoleConfigMapper;
import com.sneaklife.pms.entity.TableOpera;
import com.sneaklife.pms.service.common.CommonService;
import com.sneaklife.pms.service.common.OperaService;
import com.sneaklife.pms.service.system.authority.RoleConfigService;
import com.sneaklife.ut.exception.SneakLifeSuccessfulException;
import com.sneaklife.ut.log.LogicalLogAn;
import com.sneaklife.ut.page.PageInfo;
import com.sneaklife.ut.interfaces.ParameterTransformation;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/8/4 10:01
 */
@Service
@CacheConfig(cacheNames = "SneakLifeAuthorityManagement")
public class RoleConfigServiceImp extends CommonService implements RoleConfigService,
        ParameterTransformation<Map<String,Object>, Map<String,Object>, List<Map<String, Object>>> {

    private final RoleConfigMapper roleConfigMapper;

    private final OperaService operaService;

    @Resource(name = "roleConfigServiceImp")
    private ParameterTransformation<Map<String,Object>, Map<String,Object>, List<Map<String, Object>>> ptf;

    public RoleConfigServiceImp(RoleConfigMapper roleConfigMapper, OperaService operaService) {
        this.roleConfigMapper = roleConfigMapper;
        this.operaService = operaService;
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable
    @LogicalLogAn
    public Map<String, Object> getData(Map<String, Object> map, PageInfo pageInfo) throws Exception {
        return super.findAllPage(roleConfigMapper, map, pageInfo);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable
    @LogicalLogAn
    public TableOpera buildData(Map<String, Object> map) throws Exception{
        return operaService.buildOperaBody(map,false);
    }

    @Override
    @Transactional(rollbackFor = Exception.class,noRollbackFor = SneakLifeSuccessfulException.class)
    @SneakLifeAuthorityManagementCacheEvict
    @LogicalLogAn
    public void insert(Map<String, Object> map) throws Exception {
        insert(roleConfigMapper,map);
    }

    @Override
    @Transactional(rollbackFor = Exception.class,noRollbackFor = SneakLifeSuccessfulException.class)
    @SneakLifeAuthorityManagementCacheEvict
    @LogicalLogAn
    public void update(Map<String, Object> map) throws Exception {
        update(roleConfigMapper,map);
    }

    @Override
    @Transactional(rollbackFor = Exception.class,noRollbackFor = SneakLifeSuccessfulException.class)
    @SneakLifeAuthorityManagementCacheEvict
    @LogicalLogAn
    public void delete(Map<String, Object> map) throws Exception {
        delete(roleConfigMapper,map);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable
    @LogicalLogAn
    public List<Map<String, Object>> buildRoleTreeView(){
        List<Map<String,Object>> data = new ArrayList<>();
        List<Map<String,Object>> list = roleConfigMapper.getByIsDel(0);
        list.forEach(roleConfig -> data.add(ptf.paramTrans(new HashMap<>(), roleConfig)));
        return ptf.fixedParamTrans(data, new HashMap<>());
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable
    @LogicalLogAn
    public Map<String,Object> selectsList(Map<String,Object> map) {
        List<Map<String,Object>> roleConfigList = roleConfigMapper.getByIsDel(0);
        List<Map<String,Object>> data = new ArrayList<>();
        roleConfigList.forEach(roleConfig -> {
            Map<String,Object> m = new HashMap<>();
            m.put("name", roleConfig.get("name"));
            m.put("value", roleConfig.get("id"));
            data.add(m);
        });
        map.put("title","select role");
        map.put("data", data);
        return map;
    }

    @Override
    public Map<String, Object> paramTrans(Map<String, Object> map, Map<String,Object> roleConfig) {
        map.put("text", roleConfig.get("name"));
        map.put("url", roleConfig.get("roleMenuUrl"));
        map.put("id", roleConfig.get("id"));
        map.put("nodes", null);
        return map;
    }

    @Override
    public List<Map<String, Object>> fixedParamTrans(List<Map<String, Object>> list, Map<String, Object> map) {
        List<Map<String,Object>> data = new ArrayList<>();
        map.put("text", "系统角色管理");
        map.put("url", "#");
        map.put("nodes", list);
        data.add(map);
        return data;
    }
}
