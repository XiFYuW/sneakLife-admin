package com.sneaklife.pms.service.system.authority.imp;

import com.sneaklife.config.cache.SneakLifeAuthorityManagementCacheEvict;
import com.sneaklife.pms.dao.system.authority.roleFunction.RoleFunctionMapper;
import com.sneaklife.pms.entity.RoleFunction;
import com.sneaklife.pms.entity.modal.TableOpera;
import com.sneaklife.pms.service.common.OperaService;
import com.sneaklife.pms.service.system.authority.RoleConfigService;
import com.sneaklife.pms.service.system.authority.RoleFunctionService;
import com.sneaklife.ut.exception.SneakLifeException;
import com.sneaklife.ut.exception.SneakLifeFailureException;
import com.sneaklife.ut.exception.SneakLifeSuccessfulException;
import com.sneaklife.ut.iws.IwsContext;
import com.sneaklife.ut.iws.RespCode;
import com.sneaklife.ut.log.LogicalLogAn;
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
 */
@Service
@CacheConfig(cacheNames = "SneakLifeAuthorityManagement")
public class RoleFunctionServiceImp implements RoleFunctionService {

    private final RoleFunctionMapper roleFunctionMapper;

    private final OperaService operaService;

    private final RoleConfigService roleConfigService;

    @Autowired
    public RoleFunctionServiceImp(RoleFunctionMapper roleFunctionMapper, OperaService operaService, RoleConfigService roleConfigService) {
        this.roleFunctionMapper = roleFunctionMapper;
        this.operaService = operaService;
        this.roleConfigService = roleConfigService;
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
    public List<Map<String,Object>> getRoleFunction(Map<String, Object> map) {
        List<Map<String,Object>> roleFunctionList = roleFunctionMapper.getGroupByRoleId(String.valueOf(map.get("menuId")));
        RoleFunction roleFunction = new RoleFunction();
        StringBuilder stringBuilder = new StringBuilder();
        roleFunctionList.forEach(map1 -> stringBuilder.append(map1.get("menuId")).append(","));
        roleFunction.setMenuId(stringBuilder.toString());
        List<Map<String,Object>> data = operaService.buildRoleFunction(roleFunction, map);
        operaService.clean();
        return data;
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
        List<Map<String,Object>> upList = (List<Map<String,Object>>) map.get("up");
        Map<String,Object> root = findRootPid(upList);
        upList.remove(root);
        String roleId = String.valueOf(root.get("treeViewId"));
        roleFunctionMapper.deleteByRoleId(roleId);
        roleFunctionMapper.insertBatch(upList, roleId);
        throw new SneakLifeSuccessfulException(IwsContext.respResultXGCG());
    }

    private Map<String,Object> findRootPid(List<Map<String,Object>> upList) throws SneakLifeException{
        for(Map<String,Object> map : upList){
            if("0".equals(String.valueOf(map.get("pid")))){
                return map;
            }
        }
        throw new SneakLifeFailureException(IwsContext.respResultBody(RespCode.MSG_PARAM_ILLEGAL_NOT_ROOT.toValue(),
                RespCode.MSG_PARAM_ILLEGAL_NOT_ROOT.toMsg()));
    }

    @Override
    @Transactional(rollbackFor = Exception.class,noRollbackFor = SneakLifeSuccessfulException.class)
    @SneakLifeAuthorityManagementCacheEvict
    @LogicalLogAn
    public void delete(Map<String, Object> map) throws Exception {
        throw new SneakLifeException(IwsContext.respResultSCCG());
    }

}
