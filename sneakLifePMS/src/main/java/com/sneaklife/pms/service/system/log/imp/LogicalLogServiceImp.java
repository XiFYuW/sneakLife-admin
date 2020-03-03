package com.sneaklife.pms.service.system.log.imp;

import com.sneaklife.pms.entity.TableOpera;
import com.sneaklife.pms.service.common.CommonService;
import com.sneaklife.pms.service.common.MongoDbService;
import com.sneaklife.pms.service.common.OperaService;
import com.sneaklife.pms.service.system.log.LogicalLogService;
import com.sneaklife.ut.log.LogicalLog;
import com.sneaklife.ut.page.PageInfo;
import com.sneaklife.ut.string.StringUtil;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/1/26 12:58
 */
@Service
public class LogicalLogServiceImp extends CommonService implements LogicalLogService {

    private final OperaService operaService;

    private final MongoDbService mongoDbService;

    public LogicalLogServiceImp(OperaService operaService ,MongoDbService mongoDbService) {
        this.operaService = operaService;
        this.mongoDbService = mongoDbService;
    }

    @Override
    public void insert(Map<String, Object> map) throws Exception {

    }

    @Override
    public Map<String, Object> getData(Map<String, Object> map, PageInfo pageInfo) throws Exception {
        return mongoDbService.getPageData(pageInfo, LogicalLog.class, () -> {
            Query query = new Query();
            Criteria criteria = new Criteria();
            String sessionId = String.valueOf(map.get("sessionId"));
            String createDate = String.valueOf(map.get("createDate"));
            criteria.and("isDel").is(0);
            if(!StringUtil.isEmpty(sessionId)){
                criteria.and("sessionId").regex(sessionId);
            }
            criteria = checkDataRange(createDate, criteria);
            query.addCriteria(criteria);
            return query;
        });
    }

    @Override
    public TableOpera buildData(Map<String, Object> map) throws Exception {
        return operaService.buildOperaBody(map, false);
    }

    @Override
    public void update(Map<String, Object> map) throws Exception {

    }

    @Override
    public void delete(Map<String, Object> map) throws Exception {

    }
}
