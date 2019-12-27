package com.sneaklife.pms.service.system.authority.imp;

import com.sneaklife.config.cache.SneakLifeAuthorityManagementCacheEvict;
import com.sneaklife.pms.dao.system.authority.opera.OperaInMapper;
import com.sneaklife.pms.entity.modal.TableOpera;
import com.sneaklife.pms.service.common.CommonService;
import com.sneaklife.pms.service.common.LeftSelectViewService;
import com.sneaklife.pms.service.common.OperaService;
import com.sneaklife.pms.service.system.authority.FunctionInputService;
import com.sneaklife.ut.exception.SneakLifeSuccessfulException;
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
 * @date 2019/8/22 11:20
 */
@Service
@CacheConfig(cacheNames = "SneakLifeAuthorityManagement")
public class FunctionInputServiceImp extends CommonService implements FunctionInputService {

    private final LeftSelectViewService leftSelectViewService;

    private final OperaService operaService;

    private final OperaInMapper operaInMapper;

    @Autowired
    public FunctionInputServiceImp(LeftSelectViewService leftSelectViewServiceImp, OperaService operaService, OperaInMapper operaInMapper) {
        this.leftSelectViewService = leftSelectViewServiceImp;
        this.operaService = operaService;
        this.operaInMapper = operaInMapper;
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable
    @SneakLifeAnLog
    public List<Map<String, Object>> functionInput(Map<String, Object> map) {
        return leftSelectViewService.leftSelectsView(map);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable
    @SneakLifeAnLog
    public TableOpera functionInputTableView(Map<String, Object> map) throws Exception{
        return operaService.buildOperaBody(map,false);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable
    @SneakLifeAnLog
    public Map<String,Object> getFunctionInput(Map<String, Object> map, PageInfo pageInfo) throws Exception {
        return super.findAllPage(operaInMapper, map, pageInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, noRollbackFor = SneakLifeSuccessfulException.class)
    @SneakLifeAuthorityManagementCacheEvict
    @SneakLifeAnLog
    public void insertFunctionInput(Map<String, Object> map) throws Exception {
        insert(operaInMapper, map);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, noRollbackFor = SneakLifeSuccessfulException.class)
    @SneakLifeAuthorityManagementCacheEvict
    @SneakLifeAnLog
    public void updateFunctionInput(Map<String, Object> map) throws Exception {
        update(operaInMapper, map);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, noRollbackFor = SneakLifeSuccessfulException.class)
    @SneakLifeAuthorityManagementCacheEvict
    @SneakLifeAnLog
    public void deleteFunctionInput(Map<String, Object> map) throws Exception {
        delete(operaInMapper, map);
    }
}
