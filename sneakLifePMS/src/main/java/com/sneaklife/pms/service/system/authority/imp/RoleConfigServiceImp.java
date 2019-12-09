package com.sneaklife.pms.service.system.authority.imp;

import com.sneaklife.pms.cache.SneakLifeAuthorityManagementCacheEvict;
import com.sneaklife.pms.dao.system.authority.roleConfig.RoleConfigJpa;
import com.sneaklife.pms.dao.system.authority.roleConfig.RoleConfigMapper;
import com.sneaklife.pms.entity.RoleConfig;
import com.sneaklife.pms.entity.modal.TableOpera;
import com.sneaklife.pms.service.common.OperaService;
import com.sneaklife.pms.service.system.authority.RoleConfigService;
import com.sneaklife.ut.exception.SneakLifeSuccessfulException;
import com.sneaklife.ut.log.SneakLifeAnLog;
import com.sneaklife.ut.page.PageInfo;
import com.sneaklife.ut.interfaces.ParameterTransformation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        ParameterTransformation<RoleConfig, Map<String,Object>, List<Map<String, Object>>> {

    private static Logger log = LoggerFactory.getLogger(RoleConfigServiceImp.class);

    @Autowired
    private RoleConfigMapper roleConfigMapper;

    @Autowired
    private RoleConfigJpa roleConfigJpa;

    @Autowired
    private OperaService operaService;

    @Autowired
    private ParameterTransformation<RoleConfig, Map<String,Object>, List<Map<String, Object>>> ptf;

    @Override
    @Transactional(readOnly = true)
    @Cacheable
    @SneakLifeAnLog
    public Map<String, Object> getRoleConfig(Map<String, Object> map, PageInfo pageInfo) throws Exception {
        Page<Map<String, Object>> page = roleConfigJpa.findAllPage(getPageable(pageInfo));
        return pageToMap(page);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable
    @SneakLifeAnLog
    public TableOpera roleConfig(Map<String, Object> map) {
        map.put("isShow",0);
        return operaService.buildOperaBody(map,false);
    }

    @Override
    @Transactional(rollbackFor = Exception.class,noRollbackFor = SneakLifeSuccessfulException.class)
    @SneakLifeAuthorityManagementCacheEvict
    @SneakLifeAnLog
    public void insertRoleConfig(Map<String, Object> map) throws Exception {
        insert(roleConfigMapper,map);
    }

    @Override
    @Transactional(rollbackFor = Exception.class,noRollbackFor = SneakLifeSuccessfulException.class)
    @SneakLifeAuthorityManagementCacheEvict
    @SneakLifeAnLog
    public void updateRoleConfig(Map<String, Object> map) throws Exception {
        update(roleConfigMapper,map);
    }

    @Override
    @Transactional(rollbackFor = Exception.class,noRollbackFor = SneakLifeSuccessfulException.class)
    @SneakLifeAuthorityManagementCacheEvict
    @SneakLifeAnLog
    public void deleteRoleConfig(Map<String, Object> map) throws Exception {
        delete(roleConfigMapper,map);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable
    @SneakLifeAnLog
    public List<Map<String, Object>> buildRoleTreeView(){
        List<Map<String,Object>> data = new ArrayList<>();
        List<RoleConfig> list = roleConfigMapper.getByIsDel(0);
        list.forEach(roleConfig -> data.add(ptf.paramTrans(new HashMap<>(), roleConfig)));
        return ptf.fixedParamTrans(data, new HashMap<>());
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable
    @SneakLifeAnLog
    public Map<String,Object> selectsList(Map<String,Object> map) {
        List<RoleConfig> roleConfigList = roleConfigMapper.getByIsDel(0);
        List<Map<String,Object>> data = new ArrayList<>();
        roleConfigList.forEach(roleConfig -> {
            Map<String,Object> m = new HashMap<>();
            m.put("name", roleConfig.getName());
            m.put("value", roleConfig.getId());
            data.add(m);
        });
        map.put("title","select role");
        map.put("data", data);
        return map;
    }

    @Override
    public Map<String, Object> paramTrans(Map<String, Object> map, RoleConfig roleConfig) {
        map.put("text", roleConfig.getName());
        map.put("url", "roleFunctionTreeView");
        map.put("id", roleConfig.getId());
        map.put("nodes", null);
        return map;
    }

    @Override
    public List<Map<String, Object>> fixedParamTrans(List<Map<String, Object>> list, Map<String, Object> map) {
        List<Map<String,Object>> data = new ArrayList<>();
        map.put("text", "RoleConfig");
        map.put("url", "#");
        map.put("nodes", list);
        data.add(map);
        return data;
    }
}
