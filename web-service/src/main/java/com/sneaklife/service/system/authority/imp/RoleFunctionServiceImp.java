package com.sneaklife.service.system.authority.imp;

import com.sneaklife.dao.system.roleFunction.RoleFunctionJpa;
import com.sneaklife.dao.system.roleFunction.RoleFunctionMapper;
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
    private OperaService operaService;

    @Override
    public ResponseEntity<String> roleFunction(Map<String, Object> map) {
        return null;
    }

    @Override
    public ResponseEntity<String> getRoleFunction(Map<String, Object> map) {
        return null;
    }

    @Override
    public void insertRoleFunction(Map<String, Object> map) throws Exception {

    }

    @Override
    public void updateRoleFunction(Map<String, Object> map) throws Exception {

    }

    @Override
    public void deleteRoleFunction(Map<String, Object> map) throws Exception {

    }
}
