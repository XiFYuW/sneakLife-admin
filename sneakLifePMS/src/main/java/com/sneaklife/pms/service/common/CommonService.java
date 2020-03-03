package com.sneaklife.pms.service.common;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sneaklife.pms.dao.CommonDao;
import com.sneaklife.ut.check.CheckState;
import com.sneaklife.ut.check.SneakLifeCheckStateContext;
import com.sneaklife.ut.exception.SneakLifeException;
import com.sneaklife.ut.exception.SneakLifeFailureException;
import com.sneaklife.ut.exception.SneakLifeSuccessfulException;
import com.sneaklife.ut.iws.IwsContext;
import com.sneaklife.ut.iws.RespCode;
import com.sneaklife.ut.page.PageInfo;
import com.sneaklife.ut.string.StringUtil;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/12/10 13:36
 */
public abstract class CommonService {

    /**
     * 查询待分页信息的数据
     * @param commonDao mapper数据持久层接口对象
     * @param map 查询条件
     * @param pageInfo 分页信息
     * @return 查询分页信息之后的数据，以“content”为数据，以“totalElements”为总数量
     * @throws Exception 异常信息提示
     */
    protected Map<String, Object> findAllPage(CommonDao commonDao, Map<String, Object> map, PageInfo pageInfo) throws Exception{
        if (IwsContext.isNotNull(pageInfo.getPage()) && IwsContext.isNotNull(pageInfo.getRows())) {
            PageHelper.startPage(pageInfo.getPage(), pageInfo.getRows(), getOrderBy(pageInfo));
        } else {
            PageHelper.startPage(0, 10, getOrderBy(pageInfo));
        }
        Page<Map<String,Object>> page = commonDao.findAllPage(map);
        return pageToMap(page);
    }

    /**
     * 插入一条数据
     * @param commonDao mapper数据持久层接口对象
     * @param map 数据项
     * @throws Exception 异常信息提示
     */
    protected void insert(CommonDao commonDao, Map<String, Object> map) throws Exception {
        int t = commonDao.insert(map);
        if (t != 1) {
            throw new SneakLifeFailureException(IwsContext.respResultTJSB());
        } else {
            throw new SneakLifeSuccessfulException(IwsContext.respResultTJCG());
        }
    }

    /**
     * 更新一条数据
     * @param commonDao mapper数据持久层接口对象
     * @param map 数据项
     * @throws Exception 异常信息提示
     */
    protected void update(CommonDao commonDao, Map<String, Object> map) throws Exception {
        int t = commonDao.update(map);
        if (t != 1) {
            throw new SneakLifeFailureException(IwsContext.respResultXGSB());
        } else {
            throw new SneakLifeSuccessfulException(IwsContext.respResultXGCG());
        }
    }

    /**
     * 批量删除数据
     * @param commonDao mapper数据持久层接口对象
     * @param map 以“ids”为key，以逗号隔开的数据项为value
     * @throws Exception 异常信息提示
     */
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

    /**
     * 分页对象转map
     * @param page 分页对象
     * @return 查询分页信息之后的数据，以“content”为数据，以“totalElements”为总数量
     */
    private Map<String, Object> pageToMap(Page<Map<String, Object>> page) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("content", page);
        map.put("totalElements", page.getTotal());
        return map;
    }

    /**
     * 获取排序字段
     * @param pageInfo 分页信息
     * @return 排序字段字符串
     * @throws SneakLifeException 异常信息提示
     */
    private String getOrderBy(PageInfo pageInfo) throws SneakLifeException{
        if (!IwsContext.isNotNull(pageInfo)) {
            throw new SneakLifeException(IwsContext.respResultBody(RespCode.MSG_PAGE_ERR.toValue(), RespCode.MSG_PAGE_ERR.toMsg()));
        }
        // id asc,id desc
        StringBuilder sb = new StringBuilder(pageInfo.getSort());
        String sortOrder = pageInfo.getSortOrder();
        if(!StringUtils.isEmpty(sortOrder)){
            sb.append(" ").append(sortOrder);
        }
        return sb.toString();
    }

    /**
     * 获取分页对象（JPA）
     * @param pageInfo 分页信息
     * @return 分页对象
     * @throws SneakLifeException 异常信息提示
     */
    protected Pageable getPageable(PageInfo pageInfo) throws SneakLifeException {
        if (!IwsContext.isNotNull(pageInfo)) {
            throw new SneakLifeException(IwsContext.respResultBody(RespCode.MSG_PAGE_ERR.toValue(), RespCode.MSG_PAGE_ERR.toMsg()));
        }
        Sort.Direction direction = Sort.Direction.ASC;
        if ("desc".equals(pageInfo.getSortOrder().toLowerCase())) {
            direction = Sort.Direction.DESC;
        }
        return IwsContext.isNotNull(pageInfo.getPage()) && IwsContext.isNotNull(pageInfo.getRows()) ?
                PageRequest.of(pageInfo.getPage() - 1, pageInfo.getRows(), direction, pageInfo.getSort().split(",")) : null;
    }

    /**
     * 分页对象转map（JPA）
     * @param page 分页对象
     * @return 查询分页信息之后的数据，以“content”为数据，以“totalElements”为总数量
     */
    protected Map<String, Object> pageToMap(org.springframework.data.domain.Page page) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("content", page.getContent());
        map.put("totalElements", page.getTotalElements());
        return map;
    }

    /**
     * 检测日期起始范围是否正常
     * @param date 日期起始范围字符串
     * @param criteria JPA条件对象
     * @return JPA条件对象
     * @throws SneakLifeFailureException 异常信息提示
     */
    protected Criteria checkDataRange(String date, Criteria criteria) throws SneakLifeFailureException {
        if(!StringUtil.isEmpty(date)){
            SneakLifeCheckStateContext sneakLifeCheckStateContext = SneakLifeCheckStateContext.singleton(CheckState.DATE_RANGE);
            Map<String,Object> d = sneakLifeCheckStateContext.internal(date);
            criteria.andOperator(Criteria.where("createDate").gte(d.get("startTime")), Criteria.where("createDate").lte(d.get("endTime")));
        }
        return criteria;
    }
}

