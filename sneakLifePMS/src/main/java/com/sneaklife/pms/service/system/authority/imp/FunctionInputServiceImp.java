package com.sneaklife.pms.service.system.authority.imp;

import com.sneaklife.pms.cache.SneakLifeAuthorityManagementCacheEvict;
import com.sneaklife.pms.dao.system.authority.opera.OperaInJpa;
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
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/8/22 11:20
 */
@Service
@CacheConfig(cacheNames = "SneakLifeAuthorityManagement")
public class FunctionInputServiceImp extends CommonService implements FunctionInputService {

    @Resource(name = "leftSelectViewServiceImp")
    private LeftSelectViewService leftSelectViewService;

    @Autowired
    private OperaService operaService;

    @Autowired
    private OperaInJpa operaInJpa;

    @Autowired
    private OperaInMapper operaInMapper;

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
    public TableOpera functionInputTableView(Map<String, Object> map) {
        map.put("isShow",0);
        return operaService.buildOperaBody(map,false);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable
    @SneakLifeAnLog
    public Map<String,Object> getFunctionInput(Map<String, Object> map, PageInfo pageInfo) throws Exception {
        String menuId = String.valueOf(map.get("menuId"));
        Page<Map<String,Object>> page = operaInJpa.findAllPageByMenuId(menuId,getPageable(pageInfo));
        return pageToMap(page);
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
