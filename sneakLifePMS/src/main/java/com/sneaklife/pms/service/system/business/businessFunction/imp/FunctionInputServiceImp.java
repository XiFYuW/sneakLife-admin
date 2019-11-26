package com.sneaklife.pms.service.system.business.businessFunction.imp;

import com.sneaklife.pms.dao.system.business.businessFunction.functionInput.FunctionInputJpa;
import com.sneaklife.pms.dao.system.business.businessFunction.functionInput.FunctionInputMapper;
import com.sneaklife.pms.entity.modal.TableOpera;
import com.sneaklife.pms.service.common.CommonService;
import com.sneaklife.pms.service.common.OperaService;
import com.sneaklife.pms.service.system.authority.FunctionConfigService;
import com.sneaklife.pms.service.system.business.businessFunction.FunctionInputService;
import com.sneaklife.ut.iws.IwsContext;
import com.sneaklife.ut.page.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/8/22 11:20
 */
@Service
public class FunctionInputServiceImp extends CommonService implements FunctionInputService {

    @Autowired
    private FunctionConfigService functionConfigService;

    @Autowired
    private OperaService operaService;

    @Autowired
    private FunctionInputJpa functionInputJpa;

    @Autowired
    private FunctionInputMapper functionInputMapper;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<String> functionInput(Map<String, Object> map) {
        return functionConfigService.functionConfig(map);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<String> functionInputTableView(Map<String, Object> map) {
        map.put("isShow",0);
        TableOpera tableOpera = operaService.buildOperaBody(map,false);
        return IwsContext.respResultBodyToSC(tableOpera);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<String> getFunctionInput(Map<String, Object> map, PageInfo pageInfo) throws Exception {
        String menuId = String.valueOf(map.get("menuId"));
        Page<Map<String,Object>> page = functionInputJpa.findAllPageByMenuId(menuId,getPageable(pageInfo));
        return IwsContext.respResultBodyToSC(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertFunctionInput(Map<String, Object> map) throws Exception {
        insert(functionInputMapper, map);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateFunctionInput(Map<String, Object> map) throws Exception {
        update(functionInputMapper, map);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteFunctionInput(Map<String, Object> map) throws Exception {
        delete(functionInputMapper, map);
    }
}
