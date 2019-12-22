package com.sneaklife.pms.service.system.authority.imp;

import com.sneaklife.pms.cache.SneakLifeAuthorityManagementCacheEvict;
import com.sneaklife.pms.dao.system.authority.opera.OperaSbMapper;
import com.sneaklife.pms.entity.modal.TableOpera;
import com.sneaklife.pms.service.common.CommonService;
import com.sneaklife.pms.service.common.LeftSelectViewService;
import com.sneaklife.pms.service.common.OperaService;
import com.sneaklife.pms.service.system.authority.FunctionButtonService;
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
 * @date 2019/8/21 10:25
 */
@Service
@CacheConfig(cacheNames = "SneakLifeAuthorityManagement")
public class FunctionButtonServiceImp extends CommonService implements FunctionButtonService {

    private final OperaService operaService;

    private final OperaSbMapper operaSbMapper;

    private final LeftSelectViewService leftSelectViewService;

    @Autowired
    public FunctionButtonServiceImp(OperaService operaService, OperaSbMapper operaSbMapper, LeftSelectViewService leftSelectViewServiceImp) {
        this.operaService = operaService;
        this.operaSbMapper = operaSbMapper;
        this.leftSelectViewService = leftSelectViewServiceImp;
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable
    @SneakLifeAnLog
    public List<Map<String,Object>> functionButton(Map<String, Object> map) {
        return leftSelectViewService.leftSelectsView(map);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable
    @SneakLifeAnLog
    public TableOpera functionButtonTableView(Map<String, Object> map) {
        map.put("isShow",0);
        return operaService.buildOperaBody(map,false);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable
    @SneakLifeAnLog
    public Map<String,Object> getFunctionButton(Map<String, Object> map, PageInfo pageInfo) throws Exception{
        return super.findAllPage(operaSbMapper, map, pageInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, noRollbackFor = SneakLifeSuccessfulException.class)
    @SneakLifeAuthorityManagementCacheEvict
    @SneakLifeAnLog
    public void insertFunctionButton(Map<String, Object> map) throws Exception {
        insert(operaSbMapper, map);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, noRollbackFor = SneakLifeSuccessfulException.class)
    @SneakLifeAuthorityManagementCacheEvict
    @SneakLifeAnLog
    public void updateFunctionButton(Map<String, Object> map) throws Exception {
        update(operaSbMapper, map);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, noRollbackFor = SneakLifeSuccessfulException.class)
    @SneakLifeAuthorityManagementCacheEvict
    @SneakLifeAnLog
    public void deleteFunctionButton(Map<String, Object> map) throws Exception {
        delete(operaSbMapper, map);
    }
}
