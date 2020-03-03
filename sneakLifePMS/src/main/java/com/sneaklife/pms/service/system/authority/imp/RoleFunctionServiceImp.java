package com.sneaklife.pms.service.system.authority.imp;

import com.sneaklife.config.cache.SneakLifeAuthorityManagementCacheEvict;
import com.sneaklife.pms.entity.TableOpera;
import com.sneaklife.pms.service.common.SelectTreeService;
import com.sneaklife.pms.service.common.OperaService;
import com.sneaklife.pms.service.system.authority.RoleConfigService;
import com.sneaklife.pms.service.system.authority.RoleFunctionService;
import com.sneaklife.ut.exception.SneakLifeException;
import com.sneaklife.ut.exception.SneakLifeSuccessfulException;
import com.sneaklife.ut.iws.IwsContext;
import com.sneaklife.ut.log.LogicalLogAn;
import com.sneaklife.ut.page.PageInfo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 */
@Service
@CacheConfig(cacheNames = "SneakLifeAuthorityManagement")
public class RoleFunctionServiceImp implements RoleFunctionService {

    private final OperaService operaService;

    private final RoleConfigService roleConfigService;

    private final SelectTreeService rightSelectTreeServiceImp;

    public RoleFunctionServiceImp(OperaService operaService, RoleConfigService roleConfigService, SelectTreeService rightSelectTreeServiceImp) {
        this.operaService = operaService;
        this.roleConfigService = roleConfigService;
        this.rightSelectTreeServiceImp = rightSelectTreeServiceImp;
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable
    @LogicalLogAn
    public TableOpera buildData(Map<String, Object> map) throws Exception{
        return operaService.buildOperaBody(map,false);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable
    @LogicalLogAn
    public List<Map<String,Object>> roleFunction(Map<String, Object> map) {
        return roleConfigService.buildRoleTreeView();
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable
    @LogicalLogAn
    public List<Map<String,Object>> getRoleFunction(Map<String, Object> map) throws Exception {
        return operaService.buildSpecificRoleFunction(map);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable
    @LogicalLogAn
    public List<Map<String, Object>> roleFunctionTreeView(Map<String, Object> map) throws Exception {
        return rightSelectTreeServiceImp.selectTree(map);
    }

    @Override
    @Transactional(rollbackFor = Exception.class,noRollbackFor = SneakLifeSuccessfulException.class)
    @SneakLifeAuthorityManagementCacheEvict
    @LogicalLogAn
    public void insert(Map<String, Object> map) throws Exception {
        throw new SneakLifeException(IwsContext.respResultTJCG());
    }

    @Override
    public Map<String, Object> getData(Map<String, Object> map, PageInfo pageInfo) throws Exception {
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class,noRollbackFor = SneakLifeSuccessfulException.class)
    @SneakLifeAuthorityManagementCacheEvict
    @LogicalLogAn
    public void update(Map<String, Object> map) throws Exception {
        operaService.updateRoleFunction(map);
        throw new SneakLifeSuccessfulException(IwsContext.respResultXGCG());
    }

    @Override
    @Transactional(rollbackFor = Exception.class,noRollbackFor = SneakLifeSuccessfulException.class)
    @SneakLifeAuthorityManagementCacheEvict
    @LogicalLogAn
    public void updateSpRoleFunction(Map<String, Object> map) throws Exception {
        operaService.updateSpecificRoleFunction(map);
        throw new SneakLifeSuccessfulException(IwsContext.respResultXGCG());
    }

    @Override
    @Transactional(rollbackFor = Exception.class,noRollbackFor = SneakLifeSuccessfulException.class)
    @SneakLifeAuthorityManagementCacheEvict
    @LogicalLogAn
    public void delete(Map<String, Object> map) throws Exception {
        throw new SneakLifeException(IwsContext.respResultSCCG());
    }
}
