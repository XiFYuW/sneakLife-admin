package com.sneaklife.pms.service.system.authority.imp;

import com.sneaklife.config.cache.SneakLifeAuthorityManagementCacheEvict;
import com.sneaklife.pms.dao.system.authority.opera.ColumnsMapper;
import com.sneaklife.pms.entity.modal.TableOpera;
import com.sneaklife.pms.service.common.CommonService;
import com.sneaklife.pms.service.common.LeftSelectViewService;
import com.sneaklife.pms.service.common.OperaService;
import com.sneaklife.pms.service.system.authority.FunctionColumnsService;
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
 * @date 2019/8/22 11:38
 */
@Service
@CacheConfig(cacheNames = "SneakLifeAuthorityManagement")
public class FunctionColumnsServiceImp extends CommonService implements FunctionColumnsService {

    private final LeftSelectViewService leftSelectViewService;

    private final OperaService operaService;

    private final ColumnsMapper columnsMapper;

    @Autowired
    public FunctionColumnsServiceImp(LeftSelectViewService leftSelectViewServiceImp, OperaService operaService, ColumnsMapper columnsMapper) {
        this.leftSelectViewService = leftSelectViewServiceImp;
        this.operaService = operaService;
        this.columnsMapper = columnsMapper;
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable
    @SneakLifeAnLog
    public List<Map<String, Object>> functionColumns(Map<String, Object> map) {
        return leftSelectViewService.leftSelectsView(map);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable
    @SneakLifeAnLog
    public TableOpera functionColumnsTableView(Map<String, Object> map) throws Exception{
        return operaService.buildOperaBody(map, false);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable
    @SneakLifeAnLog
    public Map<String, Object> getFunctionColumns(Map<String, Object> map, PageInfo pageInfo) throws Exception {
        return super.findAllPage(columnsMapper, map, pageInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, noRollbackFor = SneakLifeSuccessfulException.class)
    @SneakLifeAuthorityManagementCacheEvict
    @SneakLifeAnLog
    public void insertFunctionColumns(Map<String, Object> map) throws Exception {
        insert(columnsMapper, map);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, noRollbackFor = SneakLifeSuccessfulException.class)
    @SneakLifeAuthorityManagementCacheEvict
    @SneakLifeAnLog
    public void updateFunctionColumns(Map<String, Object> map) throws Exception {
        update(columnsMapper, map);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, noRollbackFor = SneakLifeSuccessfulException.class)
    @SneakLifeAuthorityManagementCacheEvict
    @SneakLifeAnLog
    public void deleteFunctionColumns(Map<String, Object> map) throws Exception {
        delete(columnsMapper, map);
    }
}
