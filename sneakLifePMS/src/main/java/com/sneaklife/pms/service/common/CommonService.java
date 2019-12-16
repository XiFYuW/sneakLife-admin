package com.sneaklife.pms.service.common;

import com.sneaklife.pms.dao.CommonDao;
import com.sneaklife.ut.exception.SneakLifeException;
import com.sneaklife.ut.exception.SneakLifeFailureException;
import com.sneaklife.ut.exception.SneakLifeSuccessfulException;
import com.sneaklife.ut.iws.IwsContext;
import com.sneaklife.ut.iws.RespCode;
import com.sneaklife.ut.page.PageInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.HashMap;
import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/12/10 13:36
 */
public abstract class CommonService {

    protected Pageable getPageable(PageInfo pageInfo) throws SneakLifeException {
        if (!IwsContext.isNotNull(pageInfo)) {
            throw new SneakLifeException(IwsContext.respResultBody(RespCode.MSG_PAGE_ERR.toValue(), RespCode.MSG_PAGE_ERR.toMsg()));
        } else {
            Sort.Direction direction = Sort.Direction.ASC;
            if ("desc".equals(pageInfo.getSortOrder().toLowerCase())) {
                direction = Sort.Direction.DESC;
            }

            return IwsContext.isNotNull(pageInfo.getPage()) && IwsContext.isNotNull(pageInfo.getRows()) ? PageRequest.of(pageInfo.getPage(), pageInfo.getRows(), direction, new String[]{"id"}) : null;
        }
    }

    protected void insert(CommonDao commonDao, Map<String, Object> map) throws Exception {
        int t = commonDao.insert(map);
        if (t != 1) {
            throw new SneakLifeFailureException(IwsContext.respResultTJSB());
        } else {
            throw new SneakLifeSuccessfulException(IwsContext.respResultTJCG());
        }
    }

    protected void update(CommonDao commonDao, Map<String, Object> map) throws Exception {
        int t = commonDao.update(map);
        if (t != 1) {
            throw new SneakLifeFailureException(IwsContext.respResultXGSB());
        } else {
            throw new SneakLifeSuccessfulException(IwsContext.respResultXGCG());
        }
    }

    protected void delete(CommonDao commonDao, Map<String, Object> map) throws Exception {
        String[] ids = String.valueOf(map.get("ids")).split(",");
        map.put("ids", ids);
        int t = commonDao.delete(map);
        if (t <= 0) {
            throw new SneakLifeFailureException(IwsContext.respResultSCSB());
        } else {
            throw new SneakLifeSuccessfulException(IwsContext.respResultSCCG());
        }
    }

    protected Map<String, Object> pageToMap(Page<Map<String, Object>> page) {
        Map<String, Object> map = new HashMap();
        map.put("content", page.getContent());
        map.put("totalElements", page.getTotalElements());
        return map;
    }
}

