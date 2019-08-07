package com.sneaklife.service.system.authority.imp;

import com.sneaklife.common.CommonUtil;
import com.sneaklife.dao.entity.RoleConfig;
import com.sneaklife.dao.entity.RoleFunction;
import com.sneaklife.dao.entity.SystemMenu;
import com.sneaklife.dao.entity.modal.TableOpera;
import com.sneaklife.dao.system.SystemMenuJpa;
import com.sneaklife.dao.system.SystemMenuMapper;
import com.sneaklife.dao.system.authority.roleConfig.RoleConfigMapper;
import com.sneaklife.dao.system.authority.roleFunction.RoleFunctionJpa;
import com.sneaklife.dao.system.authority.roleFunction.RoleFunctionMapper;
import com.sneaklife.exception.SneakLifeException;
import com.sneaklife.service.system.OperaService;
import com.sneaklife.service.system.authority.RoleFunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author https://github.com/XiFYuW
 */
@Service
@SuppressWarnings("unchecked")
public class RoleFunctionServiceImp implements RoleFunctionService {

    @Autowired
    private RoleFunctionMapper roleFunctionMapper;

    @Autowired
    private RoleFunctionJpa roleFunctionJpa;

    @Autowired
    private RoleConfigMapper roleConfigMapper;

    @Autowired
    private SystemMenuMapper systemMenuMapper;

    @Autowired
    private SystemMenuJpa systemMenuJpa;

    @Autowired
    private OperaService operaService;

    @Override
    public ResponseEntity<String> roleFunction(Map<String, Object> map) {
        map.put("isShow",0);
        TableOpera tableOpera = operaService.buildOperaBody(map,false);
        return CommonUtil.respResultDataSUCCEED(tableOpera);
    }

    @Override
    public ResponseEntity<String> getRoleFunction(Map<String, Object> map) {
        List<RoleFunction> roleFunctionList = roleFunctionMapper.getGroupByRoleId();
        List<Map<String,Object>> data = operaService.buildRoleFunction(roleFunctionList);
        operaService.clean();
        return CommonUtil.respResultDataSUCCEED(data);
    }

    @Override
    public void insertRoleFunction(Map<String, Object> map) throws Exception {
        int t = roleFunctionMapper.insertRoleFunction(map);
        if(t != 1){
            throw new SneakLifeException(CommonUtil.respResultTJSB());
        }
        throw new SneakLifeException(CommonUtil.respResultTJCG());
    }

    @Override
    public void updateRoleFunction(Map<String, Object> map) throws Exception {
        throw new SneakLifeException(CommonUtil.respResultTJCG());
    }

    @Override
    public void deleteRoleFunction(Map<String, Object> map) throws Exception {
        throw new SneakLifeException(CommonUtil.respResultTJCG());
    }
}
