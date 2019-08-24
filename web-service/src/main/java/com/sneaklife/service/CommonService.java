package com.sneaklife.service;

import com.sneaklife.common.CommonUtil;
import com.sneaklife.dao.CommonDao;
import com.sneaklife.exception.SneakLifeException;
import com.sneaklife.page.PageInfo;
import com.sneaklife.resp.RespCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/8/12 10:06
 */
public abstract class CommonService {

    protected <T> Page<T> getPageData(Map<String, Object> map, PageInfo pageInfo, JpaSpecificationExecutor<T> jpaSpecificationExecutor) throws SneakLifeException {
        Pageable pageable = getPageable(pageInfo);
        Page<T> page = jpaSpecificationExecutor.findAll((Specification<T>) (root, criteriaQuery, criteriaBuilder) -> {
            Path<String> isDel = root.get("isDel");
            return criteriaBuilder.equal(isDel.as(Integer.class),0);
        }, pageable);
        return page;
    }

    protected <T> Page<T> getPageDataById(Map<String, Object> map, PageInfo pageInfo, JpaSpecificationExecutor<T> jpaSpecificationExecutor) throws SneakLifeException {
        Pageable pageable = getPageable(pageInfo);
        Page<T> page = jpaSpecificationExecutor.findAll((Specification<T>) (root, criteriaQuery, criteriaBuilder) -> {
            Path<String> isDel = root.get("isDel");
            Path<String> id = root.get("menuId");
            List<Predicate> list = new ArrayList<>();
            list.add(criteriaBuilder.equal(isDel.as(Integer.class),0));
            String menuId = String.valueOf(map.get("menuId"));
            list.add(criteriaBuilder.equal(id.as(String.class), menuId));
            Predicate[] predicates = new Predicate[list.size()];
            return criteriaBuilder.and(list.toArray(predicates));
        }, pageable);
        return page;
    }

    private Pageable getPageable(PageInfo pageInfo) throws SneakLifeException{
        if(!CommonUtil.isNull(pageInfo)){
            throw new SneakLifeException(CommonUtil.respResult(RespCode.MSG_PAGE_ERR.toValue(), RespCode.MSG_PAGE_ERR.toMsg()));
        }
        return PageRequest.of(pageInfo.getPage(), pageInfo.getRows(), Sort.Direction.ASC, "id");
    }

    protected void insert(CommonDao commonDao, Map<String,Object> map) throws SneakLifeException{
        int t = commonDao.insert(map);
        if(t != 1){
            throw new SneakLifeException(CommonUtil.respResultTJSB());
        }
        throw new SneakLifeException(CommonUtil.respResultTJCG());
    }

    protected void update(CommonDao commonDao, Map<String,Object> map) throws SneakLifeException{
        int t = commonDao.update(map);
        if(t != 1){
            throw new SneakLifeException(CommonUtil.respResultXGSB());
        }
        throw new SneakLifeException(CommonUtil.respResultXGCG());
    }

    protected void delete(CommonDao commonDao, Map<String,Object> map) throws SneakLifeException{
        int t = commonDao.delete(map);
        if(t != 1){
            throw new SneakLifeException(CommonUtil.respResultSCSB());
        }
        throw new SneakLifeException(CommonUtil.respResultSCCG());
    }
}
