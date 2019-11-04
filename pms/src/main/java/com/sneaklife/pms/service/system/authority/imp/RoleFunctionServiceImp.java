package com.sneaklife.pms.service.system.authority.imp;

import com.sneaklife.common.CommonUtil;
import com.sneaklife.dao.entity.RoleFunction;
import com.sneaklife.dao.entity.modal.TableOpera;
import com.sneaklife.dao.system.authority.roleFunction.RoleFunctionMapper;
import com.sneaklife.exception.SneakLifeException;
import com.sneaklife.log.SneakLifeLog;
import com.sneaklife.resp.RespCode;
import com.sneaklife.service.system.OperaService;
import com.sneaklife.service.system.authority.RoleConfigService;
import com.sneaklife.service.system.authority.RoleFunctionService;
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
    public ResponseEntity<String> roleFunctionTreeView(Map<String, Object> map) {
        map.put("isShow",0);
        TableOpera tableOpera = operaService.buildOperaBody(map,false);
        return CommonUtil.respResultDataSUCCEED(tableOpera);
    }

    @Override
    public ResponseEntity<String> roleFunction(Map<String, Object> map) {
        List<Map<String,Object>> data = roleConfigService.buildRoleTreeView();
        return CommonUtil.respResultDataSUCCEED(data);
    }

    @Override
    @SneakLifeLog
    public ResponseEntity<String> getRoleFunction(Map<String, Object> map) {
        RoleFunction roleFunction = roleFunctionMapper.getGroupByRoleId(String.valueOf(map.get("menuId")));
        List<Map<String,Object>> data = operaService.buildRoleFunction(roleFunction, map);
        operaService.clean();
        return CommonUtil.respResultDataSUCCEED(data);
    }

    @Override
    public void mutualRoleFunction(Map<String, Object> map) {

    }

    @Override
    public void insertRoleFunction(Map<String, Object> map) throws Exception {
        throw new SneakLifeException(CommonUtil.respResultTJCG());
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
        throw new SneakLifeException(CommonUtil.respResultXGCG());
    }

    private Map<String,Object> findRootPid(List<Map<String,Object>> upList) throws SneakLifeException{
        for(Map<String,Object> map : upList){
            if("0".equals(String.valueOf(map.get("pid")))){
                return map;
            }
        }
        throw new SneakLifeException(CommonUtil.respResult(RespCode.MSG_PARAM_ILLEGAL_NOT_ROOT.toValue(),
                RespCode.MSG_PARAM_ILLEGAL_NOT_ROOT.toMsg()));
    }

    @Override
    public void deleteRoleFunction(Map<String, Object> map) throws Exception {
        throw new SneakLifeException(CommonUtil.respResultSCCG());
    }

}
