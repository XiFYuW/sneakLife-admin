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

import java.util.HashMap;
import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/8/12 10:06
 */
public abstract class CommonService {

    protected Pageable getPageable(PageInfo pageInfo) throws SneakLifeException {
        if(!IwsContext.isNotNull(pageInfo)){
            throw new SneakLifeException(IwsContext.respResultBody(RespCode.MSG_PAGE_ERR.toValue(), RespCode.MSG_PAGE_ERR.toMsg()));
        }
        Sort.Direction direction = Sort.Direction.ASC;
        if("desc".equals(pageInfo.getSortOrder().toLowerCase())){
            direction = Sort.Direction.DESC;
        }
        if(!IwsContext.isNotNull(pageInfo.getPage()) || !IwsContext.isNotNull(pageInfo.getRows())){
            return null;
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

    protected Map<String,Object> pageToMap(Page<Map<String, Object>> page){
        Map<String,Object> map = new HashMap<>();
        map.put("content", page.getContent());
        map.put("totalElements", page.getTotalElements());
        return map;
    }
}
