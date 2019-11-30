package com.sneaklife.pms.service.system.authority.imp;

import com.sneaklife.pms.dao.system.authority.userRole.UserRoleJpa;
import com.sneaklife.pms.dao.system.authority.userRole.UserRoleMapper;
import com.sneaklife.pms.entity.UserRole;
import com.sneaklife.pms.entity.modal.TableOpera;
import com.sneaklife.pms.service.common.CommonService;
import com.sneaklife.pms.service.common.OperaService;
import com.sneaklife.pms.service.system.authority.UserRoleService;
import com.sneaklife.ut.exception.SneakLifeException;
import com.sneaklife.ut.iws.IwsContext;
import com.sneaklife.ut.page.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/8/11 9:38
 */
@Service
@SuppressWarnings("unchecked")
public class UserRoleServiceImp extends CommonService implements UserRoleService {

    @Autowired
    private UserRoleJpa userRoleJpa;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private OperaService operaService;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<String> userRole(Map<String, Object> map) {
        map.put("isShow",0);
        TableOpera tableOpera = operaService.buildOperaBody(map,false);
        return IwsContext.respResultBodyToSC(tableOpera);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<String> getUserRole(Map<String, Object> map, PageInfo pageInfo) throws Exception{
        Page<Map<String,Object>> page = userRoleJpa.findAllPage(getPageable(pageInfo));
        return IwsContext.respResultBodyToSC(page);
    }

    @Override
    @Transactional
    public void insertUserRole(Map<String, Object> map) throws Exception {
        throw new SneakLifeException(IwsContext.respResultTJCG());
    }

    @Override
    @Transactional
    public void updateUserRole(Map<String, Object> map) throws Exception {
        List<Map<String,Object>> upList = (List<Map<String, Object>>) map.get("up");
        int t = userRoleMapper.updateBatch(upList);
        if(t <= 0){
            throw new SneakLifeException(IwsContext.respResultXGSB());
        }
        throw new SneakLifeException(IwsContext.respResultXGCG());
    }

    @Override
    @Transactional
    public void deleteUserRole(Map<String, Object> map) throws Exception {
        throw new SneakLifeException(IwsContext.respResultSCCG());
    }
}