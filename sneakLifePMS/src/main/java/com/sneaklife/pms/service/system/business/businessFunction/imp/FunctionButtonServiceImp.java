package com.sneaklife.pms.service.system.business.businessFunction.imp;

import com.sneaklife.pms.dao.system.business.businessFunction.functionButton.FunctionButtonJpa;
import com.sneaklife.pms.dao.system.business.businessFunction.functionButton.FunctionButtonMapper;
import com.sneaklife.pms.entity.modal.TableOpera;
import com.sneaklife.pms.service.common.CommonService;
import com.sneaklife.pms.service.common.OperaService;
import com.sneaklife.pms.service.system.authority.FunctionConfigService;
import com.sneaklife.pms.service.system.business.businessFunction.FunctionButtonService;
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
 * @date 2019/8/21 10:25
 */
@Service
public class FunctionButtonServiceImp extends CommonService implements FunctionButtonService {

    @Autowired
    private FunctionConfigService functionConfigService;

    @Autowired
    private OperaService operaService;

    @Autowired
    private FunctionButtonJpa functionButtonJpa;

    @Autowired
    private FunctionButtonMapper functionButtonMapper;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<String> functionButton(Map<String, Object> map) {
        return functionConfigService.functionConfig(map);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<String> functionButtonTableView(Map<String, Object> map) {
        map.put("isShow",0);
        TableOpera tableOpera = operaService.buildOperaBody(map,false);
        return IwsContext.respResultBodyToSC(tableOpera);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<String> getFunctionButton(Map<String, Object> map, PageInfo pageInfo) throws Exception{
        String menuId = String.valueOf(map.get("menuId"));
        Page<Map<String,Object>> page = functionButtonJpa.findAllPageByMenuId(menuId,getPageable(pageInfo));
        return IwsContext.respResultBodyToSC(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertFunctionButton(Map<String, Object> map) throws Exception {
        insert(functionButtonMapper, map);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateFunctionButton(Map<String, Object> map) throws Exception {
        update(functionButtonMapper, map);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteFunctionButton(Map<String, Object> map) throws Exception {
        delete(functionButtonMapper, map);
    }
}
