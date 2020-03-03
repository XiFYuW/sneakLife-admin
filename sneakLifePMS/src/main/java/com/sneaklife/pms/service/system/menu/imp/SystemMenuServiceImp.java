package com.sneaklife.pms.service.system.menu.imp;

import com.sneaklife.config.cache.SneakLifeAuthorityManagementCacheEvict;
import com.sneaklife.pms.dao.system.SystemMenuMapper;
import com.sneaklife.pms.entity.TableOpera;
import com.sneaklife.pms.service.common.CommonService;
import com.sneaklife.pms.service.common.OperaService;
import com.sneaklife.pms.service.common.RedisService;
import com.sneaklife.pms.service.common.SelectTreeViewService;
import com.sneaklife.pms.service.system.menu.SystemMenuService;
import com.sneaklife.ut.exception.SneakLifeSuccessfulException;
import com.sneaklife.ut.interfaces.Nodes;
import com.sneaklife.ut.iws.IwsContext;
import com.sneaklife.ut.iws.RespCode;
import com.sneaklife.ut.log.LogicalLogAn;
import com.sneaklife.ut.page.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author https://github.com/XiFYuW
 */
@Service
@CacheConfig(cacheNames = "SneakLifeAuthorityManagement")
public class SystemMenuServiceImp extends CommonService implements SystemMenuService,
        Nodes<Map<String,Object>, Map<String,Object>, List<Map<String,Object>>> {

    private final SystemMenuMapper systemMenuMapper;

    private final OperaService operaService;

    private final SelectTreeViewService selectTreeViewService;

    private final RedisService redisService;

    @Resource(name = "systemMenuServiceImp")
    private Nodes<Map<String,Object>, Map<String,Object>, List<Map<String,Object>>> nodes;

    @Autowired
    public SystemMenuServiceImp(SystemMenuMapper systemMenuMapper, OperaService operaService, SelectTreeViewService selectTreeViewService, RedisService redisService) {
        this.systemMenuMapper = systemMenuMapper;
        this.operaService = operaService;
        this.selectTreeViewService = selectTreeViewService;
        this.redisService = redisService;
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable
    @LogicalLogAn
    public List<Map<String,Object>> getMenu() throws Exception{
        List<String> userInfoList = redisService.getLoginUserOpera();
        List<Map<String,Object>> list = systemMenuMapper.getByIsDel(0);
        List<Map<String,Object>> qxList = list.stream().filter(menu -> userInfoList.contains(String.valueOf(menu.get("id")))).collect(Collectors.toList());
        return qxList.stream().map(x ->  {
            x.put("son", new ArrayList<>());
            return nodes.findChildNode(x, qxList, true);
        }).filter(distinctByKey(x -> x.get("id"))).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable
    @LogicalLogAn
    public Map<String, Object> getData(Map<String, Object> map, PageInfo pageInfo) throws Exception {
        return super.findAllPage(systemMenuMapper, map, pageInfo);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable
    @LogicalLogAn
    public TableOpera buildData(Map<String, Object> map) throws Exception{
        return operaService.buildOperaBody(map, false);
    }

    @Override
    public Map<String,Object> findChildNode(Map<String,Object> node, List<Map<String,Object>> list, boolean parent) {
        List<Map<String,Object>> childMenu = new ArrayList<>();
        for(Map<String,Object> menu : list){
            if(node.get("id").equals(menu.get("pid"))){
                Map<String,Object> child = findChildNode(menu, list, false);
                childMenu.add(child);
                node.put("son", childMenu);
            }
            if(parent && node.get("pid").equals(menu.get("id"))){
                return findChildNode(menu, list, false);
            }
        }
        return node;
    }

    @Override
    @Transactional(rollbackFor = Exception.class,noRollbackFor = SneakLifeSuccessfulException.class)
    @SneakLifeAuthorityManagementCacheEvict
    @LogicalLogAn
    public void insert(Map<String, Object> map) throws Exception {
        insert(systemMenuMapper,map);
    }

    @Override
    @Transactional(rollbackFor = Exception.class,noRollbackFor = SneakLifeSuccessfulException.class)
    @SneakLifeAuthorityManagementCacheEvict
    @LogicalLogAn
    public void update(Map<String, Object> map) throws Exception {
        update(systemMenuMapper,map);
    }

    @Override
    @Transactional(rollbackFor = Exception.class,noRollbackFor = SneakLifeSuccessfulException.class)
    @SneakLifeAuthorityManagementCacheEvict
    @LogicalLogAn
    public void delete(Map<String, Object> map) throws Exception {
        delete(systemMenuMapper,map);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable
    public Map<String, Object> selectTreeView(Map<String, Object> map) {
        return selectTreeViewService.selectTreeView(map);
    }

    @Override
    public void logOut() throws Exception {
       redisService.logOut();
       throw new SneakLifeSuccessfulException(IwsContext.respResultBody(RespCode.MSG_LOG_OUT_SUCCEED.toValue(),
               RespCode.MSG_LOG_OUT_SUCCEED.toMsg()));
    }
}
