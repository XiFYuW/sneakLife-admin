package com.sneaklife.pms.service.system.business.businessFunction.imp;

import com.sneaklife.pms.dao.system.business.businessFunction.functionButton.FunctionButtonJpa;
import com.sneaklife.pms.dao.system.business.businessFunction.functionButton.FunctionButtonMapper;
import com.sneaklife.pms.entity.OperaSb;
import com.sneaklife.pms.entity.modal.TableOpera;
import com.sneaklife.pms.service.common.CommonService;
import com.sneaklife.pms.service.common.OperaService;
import com.sneaklife.pms.service.system.authority.FunctionConfigService;
import com.sneaklife.pms.service.system.business.businessFunction.FunctionButtonService;
import com.sneaklife.ut.code.page.PageInfo;
import com.sneaklife.ut.common.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
    public ResponseEntity<String> functionButton(Map<String, Object> map) {
        return functionConfigService.functionConfig(map);
    }

    @Override
    public ResponseEntity<String> functionButtonTableView(Map<String, Object> map) {
        map.put("isShow",0);
        TableOpera tableOpera = operaService.buildOperaBody(map,false);
        return CommonUtil.respResultDataSUCCEED(tableOpera);
    }

    @Override
    public ResponseEntity<String> getFunctionButton(Map<String, Object> map, PageInfo pageInfo) throws Exception{
        Page<OperaSb> page = getPageDataById(map, pageInfo, functionButtonJpa);
        return CommonUtil.respResultDataSUCCEED(page);
    }

    @Override
    public void insertFunctionButton(Map<String, Object> map) throws Exception {
        insert(functionButtonMapper, map);
    }

    @Override
    public void updateFunctionButton(Map<String, Object> map) throws Exception {
        update(functionButtonMapper, map);
    }

    @Override
    public void deleteFunctionButton(Map<String, Object> map) throws Exception {
        delete(functionButtonMapper, map);
    }
}
