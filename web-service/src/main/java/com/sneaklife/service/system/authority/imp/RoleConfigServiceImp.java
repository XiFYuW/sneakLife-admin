package com.sneaklife.service.system.authority.imp;

import com.sneaklife.common.CommonUtil;
import com.sneaklife.dao.entity.RoleConfig;
import com.sneaklife.dao.entity.modal.TableOpera;
import com.sneaklife.dao.system.authority.roleConfig.RoleConfigJpa;
import com.sneaklife.dao.system.authority.roleConfig.RoleConfigMapper;
import com.sneaklife.exception.SneakLifeException;
import com.sneaklife.page.PageInfo;
import com.sneaklife.resp.RespCode;
import com.sneaklife.service.system.OperaService;
import com.sneaklife.service.system.authority.RoleConfigService;
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
public class RoleConfigServiceImp implements RoleConfigService {

    private static Logger log = LoggerFactory.getLogger(RoleConfigServiceImp.class);

    @Autowired
    private RoleConfigMapper roleConfigMapper;

    @Autowired
    private RoleConfigJpa roleConfigJpa;

    @Autowired
    private OperaService operaService;

    @Override
    public void insertRoleConfig(Map<String, Object> map) throws Exception {
        int t = roleConfigMapper.insertRoleConfig(map);
        if(t != 1){
            throw new SneakLifeException(CommonUtil.respResultTJSB());
        }
        throw new SneakLifeException(CommonUtil.respResultTJCG());
    }

    @Override
    public ResponseEntity<String> getRoleConfig(Map<String, Object> map, PageInfo pageInfo) throws Exception {
        if(!CommonUtil.isNull(pageInfo)){
            return CommonUtil.respResult(RespCode.MSG_PAGE_ERR.toValue(), RespCode.MSG_PAGE_ERR.toMsg());
        }
        Pageable pageable = PageRequest.of(pageInfo.getPage(), pageInfo.getRows(), Sort.Direction.ASC, "id");
        Page<RoleConfig> page = roleConfigJpa.findAll((Specification<RoleConfig>) (root, criteriaQuery, criteriaBuilder) -> {
            Path<String> isDel = root.get("isDel");
            return criteriaBuilder.equal(isDel.as(Integer.class),0);
        }, pageable);
        return CommonUtil.respResultDataSUCCEED(page);
    }

    @Override
    public ResponseEntity<String> roleConfig(Map<String, Object> map) throws Exception {
        map.put("isShow",0);
        TableOpera tableOpera = operaService.buildOperaBody(map,false);
        return CommonUtil.respResultDataSUCCEED(tableOpera);
    }

    @Override
    public void updateRoleConfig(Map<String, Object> map) throws Exception {
        int t = roleConfigMapper.updateRoleConfig(map);
        if(t != 1){
            throw new SneakLifeException(CommonUtil.respResultXGSB());
        }
        throw new SneakLifeException(CommonUtil.respResultXGCG());
    }

    @Override
    public void deleteRoleConfig(Map<String, Object> map) throws Exception {
        int t = roleConfigMapper.deleteRoleConfig(map);
        if(t != 1){
            throw new SneakLifeException(CommonUtil.respResultSCSB());
        }
        throw new SneakLifeException(CommonUtil.respResultSCCG());
    }
}
