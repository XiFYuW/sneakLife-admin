package com.sneaklife.pms.service.system.authority.imp;

import com.sneaklife.pms.cache.SneakLifeAuthorityManagementCacheEvict;
import com.sneaklife.pms.dao.system.authority.opera.OperaSbJpa;
import com.sneaklife.pms.dao.system.authority.opera.OperaSbMapper;
import com.sneaklife.pms.entity.modal.TableOpera;
import com.sneaklife.pms.service.common.CommonService;
import com.sneaklife.pms.service.common.LeftSelectViewService;
import com.sneaklife.pms.service.common.OperaService;
import com.sneaklife.pms.service.system.authority.FunctionButtonService;
import com.sneaklife.ut.exception.SneakLifeSuccessfulException;
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
 * @date 2019/8/21 10:25
 */
@Service
@CacheConfig(cacheNames = "SneakLifeAuthorityManagement")
public class FunctionButtonServiceImp extends CommonService implements FunctionButtonService {

    @Autowired
    private OperaService operaService;

    @Autowired
    private OperaSbJpa operaSbJpa;

    @Autowired
    private OperaSbMapper operaSbMapper;

    @Resource(name = "leftSelectViewServiceImp")
    private LeftSelectViewService leftSelectViewService;

    @Override
    @Transactional(readOnly = true)
    @Cacheable
    public List<Map<String,Object>> functionButton(Map<String, Object> map) {
        return leftSelectViewService.leftSelectsView(map);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable
    public TableOpera functionButtonTableView(Map<String, Object> map) {
        map.put("isShow",0);
        return operaService.buildOperaBody(map,false);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable
    public Map<String,Object> getFunctionButton(Map<String, Object> map, PageInfo pageInfo) throws Exception{
        String menuId = String.valueOf(map.get("menuId"));
        Page<Map<String,Object>> page = operaSbJpa.findAllPageByMenuId(menuId,getPageable(pageInfo));
        return pageToMap(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, noRollbackFor = SneakLifeSuccessfulException.class)
    @SneakLifeAuthorityManagementCacheEvict
    public void insertFunctionButton(Map<String, Object> map) throws Exception {
        insert(operaSbMapper, map);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, noRollbackFor = SneakLifeSuccessfulException.class)
    @SneakLifeAuthorityManagementCacheEvict
    public void updateFunctionButton(Map<String, Object> map) throws Exception {
        update(operaSbMapper, map);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, noRollbackFor = SneakLifeSuccessfulException.class)
    @SneakLifeAuthorityManagementCacheEvict
    public void deleteFunctionButton(Map<String, Object> map) throws Exception {
        delete(operaSbMapper, map);
    }
}
