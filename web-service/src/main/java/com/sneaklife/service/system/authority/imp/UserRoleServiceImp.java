package com.sneaklife.service.system.authority.imp;

import com.sneaklife.common.CommonUtil;
import com.sneaklife.dao.entity.UserRole;
import com.sneaklife.dao.entity.modal.TableOpera;
import com.sneaklife.dao.system.authority.userRole.UserRoleJpa;
import com.sneaklife.dao.system.authority.userRole.UserRoleMapper;
import com.sneaklife.exception.SneakLifeException;
import com.sneaklife.page.PageInfo;
import com.sneaklife.resp.RespCode;
import com.sneaklife.service.system.OperaService;
import com.sneaklife.service.system.authority.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Path;
import java.util.List;
import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/8/11 9:38
 */
@Service
public class UserRoleServiceImp implements UserRoleService {

    @Autowired
    private UserRoleJpa userRoleJpa;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private OperaService operaService;

    @Override
    public ResponseEntity<String> userRole(Map<String, Object> map) {
        map.put("isShow",0);
        TableOpera tableOpera = operaService.buildOperaBody(map,false);
        return CommonUtil.respResultDataSUCCEED(tableOpera);
    }

    @Override
    public ResponseEntity<String> getUserRole(Map<String, Object> map, PageInfo pageInfo) throws Exception{
        if(!CommonUtil.isNull(pageInfo)){
            return CommonUtil.respResult(RespCode.MSG_PAGE_ERR.toValue(), RespCode.MSG_PAGE_ERR.toMsg());
        }
        Pageable pageable = PageRequest.of(pageInfo.getPage(), pageInfo.getRows(), Sort.Direction.ASC, "id");
        Page<UserRole> page = userRoleJpa.findAll((Specification<UserRole>) (root, criteriaQuery, criteriaBuilder) -> {
            Path<String> isDel = root.get("isDel");
            return criteriaBuilder.equal(isDel.as(Integer.class),0);
        }, pageable);
        List<UserRole> content = page.getContent();
        content.forEach(userRole -> {
            UserRole temp = userRoleMapper.getAllNameById(userRole);
            userRole.setRoleName(temp.getRoleName());
            userRole.setUserName(temp.getUserName());
            userRole.setValue(temp.getValue());
            userRole.setText(temp.getRoleName());
        });
        return CommonUtil.respResultDataSUCCEED(page);
    }

    @Override
    public void insertUserRole(Map<String, Object> map) throws Exception {
        throw new SneakLifeException(CommonUtil.respResultTJCG());
    }

    @Override
    public void updateUserRole(Map<String, Object> map) throws Exception {
        throw new SneakLifeException(CommonUtil.respResultXGCG());
    }

    @Override
    public void deleteUserRole(Map<String, Object> map) throws Exception {
        throw new SneakLifeException(CommonUtil.respResultSCCG());
    }
}
