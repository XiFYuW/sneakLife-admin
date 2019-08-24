package com.sneaklife.service.system.business.businessFunction.imp;

import com.sneaklife.common.CommonUtil;
import com.sneaklife.dao.entity.OperaSb;
import com.sneaklife.dao.entity.modal.TableOpera;
import com.sneaklife.dao.system.business.businessFunction.functionButton.FunctionButtonJpa;
import com.sneaklife.dao.system.business.businessFunction.functionButton.FunctionButtonMapper;
import com.sneaklife.page.PageInfo;
import com.sneaklife.service.CommonService;
import com.sneaklife.service.system.OperaService;
import com.sneaklife.service.system.authority.FunctionConfigService;
import com.sneaklife.service.system.business.businessFunction.FunctionButtonService;
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
