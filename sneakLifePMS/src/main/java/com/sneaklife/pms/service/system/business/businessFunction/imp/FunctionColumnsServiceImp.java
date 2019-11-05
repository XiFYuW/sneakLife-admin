package com.sneaklife.pms.service.system.business.businessFunction.imp;

import com.sneaklife.dao.entity.Columns;
import com.sneaklife.dao.entity.modal.TableOpera;
import com.sneaklife.dao.system.business.businessFunction.fcuntionColumns.FunctionColumnsJpa;
import com.sneaklife.dao.system.business.businessFunction.fcuntionColumns.FunctionColumnsMapper;
import com.sneaklife.pms.service.CommonService;
import com.sneaklife.pms.service.system.OperaService;
import com.sneaklife.pms.service.system.authority.FunctionConfigService;
import com.sneaklife.pms.service.system.business.businessFunction.FunctionColumnsService;
import com.sneaklife.util.code.page.PageInfo;
import com.sneaklife.util.common.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/8/22 11:38
 */
@Service
public class FunctionColumnsServiceImp extends CommonService implements FunctionColumnsService {

    @Autowired
    private FunctionConfigService functionConfigService;

    @Autowired
    private OperaService operaService;

    @Autowired
    private FunctionColumnsJpa functionColumnsJpa;

    @Autowired
    private FunctionColumnsMapper functionColumnsMapper;

    @Override
    public ResponseEntity<String> functionColumns(Map<String, Object> map) {
        return functionConfigService.functionConfig(map);
    }

    @Override
    public ResponseEntity<String> functionColumnsTableView(Map<String, Object> map) {
        map.put("isShow",0);
        TableOpera tableOpera = operaService.buildOperaBody(map,false);
        return CommonUtil.respResultDataSUCCEED(tableOpera);
    }

    @Override
    public ResponseEntity<String> getFunctionColumns(Map<String, Object> map, PageInfo pageInfo) throws Exception {
        Page<Columns> page = getPageDataById(map, pageInfo, functionColumnsJpa);
        return CommonUtil.respResultDataSUCCEED(page);
    }

    @Override
    public void insertFunctionColumns(Map<String, Object> map) throws Exception {
        insert(functionColumnsMapper, map);
    }

    @Override
    public void updateFunctionColumns(Map<String, Object> map) throws Exception {
        update(functionColumnsMapper, map);
    }

    @Override
    public void deleteFunctionColumns(Map<String, Object> map) throws Exception {
        delete(functionColumnsMapper, map);
    }
}
