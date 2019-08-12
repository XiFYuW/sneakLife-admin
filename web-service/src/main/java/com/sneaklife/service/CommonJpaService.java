package com.sneaklife.service;

import com.sneaklife.common.CommonUtil;
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
import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/8/12 10:06
 */
public abstract class CommonJpaService {

    protected <T> Page<T> getPageData(Map<String, Object> map, PageInfo pageInfo, JpaSpecificationExecutor<T> jpaSpecificationExecutor) throws SneakLifeException {
        if(!CommonUtil.isNull(pageInfo)){
            throw new SneakLifeException(CommonUtil.respResult(RespCode.MSG_PAGE_ERR.toValue(), RespCode.MSG_PAGE_ERR.toMsg()));
        }
        Pageable pageable = PageRequest.of(pageInfo.getPage(), pageInfo.getRows(), Sort.Direction.ASC, "id");
        Page<T> page = jpaSpecificationExecutor.findAll((Specification<T>) (root, criteriaQuery, criteriaBuilder) -> {
            Path<String> isDel = root.get("isDel");
            return criteriaBuilder.equal(isDel.as(Integer.class),0);
        }, pageable);
        return page;
    }
}
