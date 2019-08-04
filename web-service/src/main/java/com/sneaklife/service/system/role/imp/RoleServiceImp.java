package com.sneaklife.service.system.role.imp;

import com.sneaklife.common.CommonUtil;
import com.sneaklife.dao.entity.Role;
import com.sneaklife.dao.entity.modal.TableOpera;
import com.sneaklife.dao.system.role.RoleJpa;
import com.sneaklife.dao.system.role.RoleMapper;
import com.sneaklife.exception.SneakLifeException;
import com.sneaklife.page.PageInfo;
import com.sneaklife.resp.RespCode;
import com.sneaklife.service.system.OperaService;
import com.sneaklife.service.system.role.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Path;
import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/8/4 10:01
 */
@Service
public class RoleServiceImp implements RoleService {

    private static Logger log = LoggerFactory.getLogger(RoleServiceImp.class);

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleJpa roleJpa;

    @Autowired
    private OperaService operaService;

    @Override
    public void insertRole(Map<String, Object> map) throws Exception {
        int t = roleMapper.insertRole(map);
        if(t != 1){
            throw new SneakLifeException(CommonUtil.respResultTJSB());
        }
        throw new SneakLifeException(CommonUtil.respResultTJCG());
    }

    @Override
    public ResponseEntity<String> getRole(Map<String, Object> map, PageInfo pageInfo) throws Exception {
        if(!CommonUtil.isNull(pageInfo)){
            return CommonUtil.respResult(RespCode.MSG_PAGE_ERR.toValue(), RespCode.MSG_PAGE_ERR.toMsg());
        }
        Pageable pageable = PageRequest.of(pageInfo.getPage(), pageInfo.getRows(), Sort.Direction.ASC, "id");
        Page<Role> page = roleJpa.findAll((Specification<Role>) (root, criteriaQuery, criteriaBuilder) -> {
            Path<String> isDel = root.get("isDel");
            return criteriaBuilder.equal(isDel.as(Integer.class),0);
        }, pageable);
        return CommonUtil.respResultDataSUCCEED(page);
    }

    @Override
    public ResponseEntity<String> role(Map<String, Object> map) throws Exception {
        map.put("isShow",0);
        TableOpera tableOpera = operaService.buildOperaBody(map,false);
        return CommonUtil.respResultDataSUCCEED(tableOpera);
    }

    @Override
    public void updateRole(Map<String, Object> map) throws Exception {
        int t = roleMapper.updateRole(map);
        if(t != 1){
            throw new SneakLifeException(CommonUtil.respResultXGSB());
        }
        throw new SneakLifeException(CommonUtil.respResultXGCG());
    }

    @Override
    public void deleteRole(Map<String, Object> map) throws Exception {
        int t = roleMapper.deleteRole(map);
        if(t != 1){
            throw new SneakLifeException(CommonUtil.respResultSCSB());
        }
        throw new SneakLifeException(CommonUtil.respResultSCCG());
    }
}
