package com.sneaklife.pms.service.common;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sneaklife.pms.dao.CommonDao;
import com.sneaklife.ut.exception.SneakLifeException;
import com.sneaklife.ut.exception.SneakLifeFailureException;
import com.sneaklife.ut.exception.SneakLifeSuccessfulException;
import com.sneaklife.ut.iws.IwsContext;
import com.sneaklife.ut.iws.RespCode;
import com.sneaklife.ut.page.PageInfo;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/12/10 13:36
 */
public abstract class CommonService {

    protected Map<String, Object> findAllPage(CommonDao commonDao, Map<String, Object> map, PageInfo pageInfo) throws Exception{
        if (IwsContext.isNotNull(pageInfo.getPage()) && IwsContext.isNotNull(pageInfo.getRows())) {
            PageHelper.startPage(pageInfo.getPage(), pageInfo.getRows(), getOrderBy(pageInfo));
        } else {
            PageHelper.startPage(0, 10, getOrderBy(pageInfo));
        }
        Page<Map<String,Object>> page = commonDao.findAllPage(map);
        return pageToMap(page);
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

    private Map<String, Object> pageToMap(com.github.pagehelper.Page<Map<String, Object>> page) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("content", page);
        map.put("totalElements", page.getTotal());
        return map;
    }

    private String getOrderBy(PageInfo pageInfo) throws SneakLifeException{
        if (!IwsContext.isNotNull(pageInfo)) {
            throw new SneakLifeException(IwsContext.respResultBody(RespCode.MSG_PAGE_ERR.toValue(), RespCode.MSG_PAGE_ERR.toMsg()));
        } else {
            // id asc,id desc
            StringBuilder sb = new StringBuilder(pageInfo.getSort());
            String sortOrder = pageInfo.getSortOrder();
            if(!StringUtils.isEmpty(sortOrder)){
                sb.append(" ").append(sortOrder);
            }
            return sb.toString();
        }
    }
}

