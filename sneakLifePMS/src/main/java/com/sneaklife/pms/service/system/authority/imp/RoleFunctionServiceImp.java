package com.sneaklife.pms.service.system.authority.imp;

import com.sneaklife.pms.dao.system.authority.roleFunction.RoleFunctionMapper;
import com.sneaklife.pms.entity.RoleFunction;
import com.sneaklife.pms.entity.modal.TableOpera;
import com.sneaklife.pms.service.common.OperaService;
import com.sneaklife.pms.service.system.authority.RoleConfigService;
import com.sneaklife.pms.service.system.authority.RoleFunctionService;
import com.sneaklife.ut.exception.SneakLifeException;
import com.sneaklife.ut.iws.IwsContext;
import com.sneaklife.ut.iws.RespCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 */
@Service
@SuppressWarnings("unchecked")
public class RoleFunctionServiceImp implements RoleFunctionService {

    @Autowired
    private RoleFunctionMapper roleFunctionMapper;

    @Autowired
    private OperaService operaService;

    @Autowired
    private RoleConfigService roleConfigService;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<String> roleFunctionTreeView(Map<String, Object> map) {
        map.put("isShow",0);
        TableOpera tableOpera = operaService.buildOperaBody(map,false);
        return IwsContext.respResultBodyToSC(tableOpera);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<String> roleFunction(Map<String, Object> map) {
        List<Map<String,Object>> data = roleConfigService.buildRoleTreeView();
        return IwsContext.respResultBodyToSC(data);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<String> getRoleFunction(Map<String, Object> map) {
        List<Map<String,Object>> roleFunctionList = roleFunctionMapper.getGroupByRoleId(String.valueOf(map.get("menuId")));
        RoleFunction roleFunction = new RoleFunction();
        StringBuilder stringBuilder = new StringBuilder();
        roleFunctionList.forEach(map1 -> {
            stringBuilder.append(map1.get("menuId")).append(",");
        });
        roleFunction.setMenuId(stringBuilder.toString());
        List<Map<String,Object>> data = operaService.buildRoleFunction(roleFunction, map);
        operaService.clean();
        return IwsContext.respResultBodyToSC(data);
    }

    @Override
    @Transactional
    public void insertRoleFunction(Map<String, Object> map) throws Exception {
        throw new SneakLifeException(IwsContext.respResultTJCG());
    }

    @Override
    @Transactional
    public void updateRoleFunction(Map<String, Object> map) throws Exception {
        List<Map<String,Object>> upList = (List<Map<String,Object>>) map.get("up");
        Map<String,Object> root = findRootPid(upList);
        upList.remove(root);
        String roleId = String.valueOf(root.get("treeViewId"));
        roleFunctionMapper.deleteByRoleId(roleId);
        roleFunctionMapper.insertBatch(upList, roleId);
        throw new SneakLifeException(IwsContext.respResultXGCG());
    }

    private Map<String,Object> findRootPid(List<Map<String,Object>> upList) throws SneakLifeException{
        for(Map<String,Object> map : upList){
            if("0".equals(String.valueOf(map.get("pid")))){
                return map;
            }
        }
        throw new SneakLifeException(IwsContext.respResultBody(RespCode.MSG_PARAM_ILLEGAL_NOT_ROOT.toValue(),
                RespCode.MSG_PARAM_ILLEGAL_NOT_ROOT.toMsg()));
    }

    @Override
    @Transactional
    public void deleteRoleFunction(Map<String, Object> map) throws Exception {
        throw new SneakLifeException(IwsContext.respResultSCCG());
    }

}