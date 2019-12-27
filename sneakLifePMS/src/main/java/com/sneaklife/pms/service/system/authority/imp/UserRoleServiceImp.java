package com.sneaklife.pms.service.system.authority.imp;

import com.sneaklife.config.cache.SneakLifeAuthorityManagementCacheEvict;
import com.sneaklife.pms.dao.system.authority.userRole.UserRoleMapper;
import com.sneaklife.pms.entity.modal.TableOpera;
import com.sneaklife.pms.service.common.CommonService;
import com.sneaklife.pms.service.common.OperaService;
import com.sneaklife.pms.service.system.authority.UserRoleService;
import com.sneaklife.ut.exception.SneakLifeFailureException;
import com.sneaklife.ut.exception.SneakLifeSuccessfulException;
import com.sneaklife.ut.iws.IwsContext;
import com.sneaklife.ut.log.SneakLifeAnLog;
import com.sneaklife.ut.page.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/8/11 9:38
 */
@Service
@CacheConfig(cacheNames = "SneakLifeAuthorityManagement")
public class UserRoleServiceImp extends CommonService implements UserRoleService {

    private final UserRoleMapper userRoleMapper;

    private final OperaService operaService;

    @Autowired
    public UserRoleServiceImp(UserRoleMapper userRoleMapper, OperaService operaService) {
        this.userRoleMapper = userRoleMapper;
        this.operaService = operaService;
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable
    @SneakLifeAnLog
    public TableOpera userRole(Map<String, Object> map) throws Exception{
        return operaService.buildOperaBody(map,false);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable
    @SneakLifeAnLog
    public Map<String,Object> getUserRole(Map<String, Object> map, PageInfo pageInfo) throws Exception{
        return super.findAllPage(userRoleMapper, map, pageInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class,noRollbackFor = SneakLifeSuccessfulException.class)
    @SneakLifeAuthorityManagementCacheEvict
    @SneakLifeAnLog
    public void insertUserRole(Map<String, Object> map) throws Exception {
        throw new SneakLifeSuccessfulException(IwsContext.respResultTJCG());
    }

    @Override
    @Transactional(rollbackFor = Exception.class,noRollbackFor = SneakLifeSuccessfulException.class)
    @SneakLifeAuthorityManagementCacheEvict
    @SneakLifeAnLog
    public void updateUserRole(Map<String, Object> map) throws Exception {
        List<Map<String,Object>> upList = (List<Map<String, Object>>) map.get("up");
        int t = userRoleMapper.updateBatch(upList);
        if(t <= 0){
            throw new SneakLifeFailureException(IwsContext.respResultXGSB());
        }
        throw new SneakLifeSuccessfulException(IwsContext.respResultXGCG());
    }

    @Override
    @Transactional(rollbackFor = Exception.class,noRollbackFor = SneakLifeSuccessfulException.class)
    @SneakLifeAuthorityManagementCacheEvict
    @SneakLifeAnLog
    public void deleteUserRole(Map<String, Object> map) throws Exception {
        throw new SneakLifeSuccessfulException(IwsContext.respResultSCCG());
    }
}
