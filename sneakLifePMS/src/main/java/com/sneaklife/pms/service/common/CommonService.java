package com.sneaklife.pms.service.common;

import com.sneaklife.pms.dao.CommonDao;
import com.sneaklife.ut.exception.SneakLifeException;
import com.sneaklife.ut.iws.IwsContext;
import com.sneaklife.ut.page.PageInfo;
import com.sneaklife.ut.iws.RespCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.util.ObjectUtils;

import javax.persistence.criteria.Path;
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

    protected Pageable getPageable(PageInfo pageInfo) throws SneakLifeException {
        if(ObjectUtils.isEmpty(pageInfo)){
            throw new SneakLifeException(IwsContext.respResultBody(RespCode.MSG_PAGE_ERR.toValue(), RespCode.MSG_PAGE_ERR.toMsg()));
        }
        Sort.Direction direction = Sort.Direction.ASC;
        if("desc".equals(pageInfo.getSortOrder().toLowerCase())){
            direction = Sort.Direction.DESC;
        }
        return PageRequest.of(pageInfo.getPage(), pageInfo.getRows(), direction, "id");
    }

    protected void insert(CommonDao commonDao, Map<String,Object> map) throws SneakLifeException{
        int t = commonDao.insert(map);
        if(t != 1){
            throw new SneakLifeException(IwsContext.respResultTJSB());
        }
        throw new SneakLifeException(IwsContext.respResultTJCG());
    }

    protected void update(CommonDao commonDao, Map<String,Object> map) throws SneakLifeException{
        int t = commonDao.update(map);
        if(t != 1){
            throw new SneakLifeException(IwsContext.respResultXGSB());
        }
        throw new SneakLifeException(IwsContext.respResultXGCG());
    }

    protected void delete(CommonDao commonDao, Map<String,Object> map) throws SneakLifeException{
        int t = commonDao.delete(map);
        if(t != 1){
            throw new SneakLifeException(IwsContext.respResultSCSB());
        }
        throw new SneakLifeException(IwsContext.respResultSCCG());
    }
}
