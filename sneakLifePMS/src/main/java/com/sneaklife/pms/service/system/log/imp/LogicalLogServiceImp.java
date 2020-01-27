package com.sneaklife.pms.service.system.log.imp;

import com.sneaklife.pms.entity.modal.TableOpera;
import com.sneaklife.pms.service.common.CommonService;
import com.sneaklife.pms.service.common.OperaService;
import com.sneaklife.pms.service.system.log.LogicalLogService;
import com.sneaklife.ut.log.SneakLifeLogDB;
import com.sneaklife.ut.page.PageInfo;
import com.sneaklife.ut.string.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
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

    private final MongoTemplate mongoTemplate;

    @Autowired
    public LogicalLogServiceImp(OperaService operaService ,MongoTemplate mongoTemplate) {
        this.operaService = operaService;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void insertLogicalLog(Map<String, Object> map) throws Exception {

    }

    @Override
    public Map<String, Object> getLogicalLog(Map<String, Object> map, PageInfo pageInfo) throws Exception {
        return getMongoDBDataPage(new Query(), mongoTemplate, pageInfo, SneakLifeLogDB.class, query -> {
            Criteria criteria = new Criteria();
            String sessionId = String.valueOf(map.get("sessionId"));
            criteria.and("isDel").is(0);
            if(!StringUtil.isEmpty(sessionId)){
                criteria.and("sessionId").regex(sessionId);
            }
            query.addCriteria(criteria);
            return query;
        });
    }

    @Override
    public TableOpera logicalLog(Map<String, Object> map) throws Exception {
        return operaService.buildOperaBody(map, false);
    }

    @Override
    public void updateLogicalLog(Map<String, Object> map) throws Exception {

    }

    @Override
    public void deleteLogicalLog(Map<String, Object> map) throws Exception {

    }
}
