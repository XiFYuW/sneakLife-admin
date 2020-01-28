package com.sneaklife.pms.service.system.log.imp;

import com.sneaklife.pms.entity.modal.TableOpera;
import com.sneaklife.pms.service.common.CommonService;
import com.sneaklife.pms.service.common.OperaService;
import com.sneaklife.pms.service.system.log.AccessLogService;
import com.sneaklife.ut.log.AccessLog;
import com.sneaklife.ut.page.PageInfo;
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
public class AccessLogServiceImp extends CommonService implements AccessLogService {

    private final OperaService operaService;

    private final MongoTemplate mongoTemplate;

    @Autowired
    public AccessLogServiceImp(OperaService operaService , MongoTemplate mongoTemplate) {
        this.operaService = operaService;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void insert(Map<String, Object> map) throws Exception {

    }

    @Override
    public Map<String, Object> getData(Map<String, Object> map, PageInfo pageInfo) throws Exception {
        return getMongoDBDataPage(new Query(), mongoTemplate, pageInfo, AccessLog.class, query -> {
            Criteria criteria = new Criteria();
            criteria.and("isDel").is(0);
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
