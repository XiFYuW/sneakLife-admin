package com.sneaklife.pms.job;

import com.sneaklife.pms.service.common.MongoDbService;
import com.sneaklife.ut.date.DateUtil;
import com.sneaklife.ut.log.AccessLog;
import com.sneaklife.ut.log.LogicalLog;
import com.sneaklife.ut.string.StringUtil;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.log.XxlJobLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/2/2 16:01
 */
@Component
public class CleanLogJob {

    private final MongoDbService mongoDbService;

    private static final long DEFAULT_DAY = 30;

    @Autowired
    public CleanLogJob(MongoDbService mongoDbService) {
        this.mongoDbService = mongoDbService;
    }

    @XxlJob("cleanLogJobHandler")
    public ReturnT<String> cleanLogJobHandler(String param) throws Exception {
        long day = DEFAULT_DAY;
        if (!StringUtil.isEmpty(param)) {
            day = Long.valueOf(param);
        }
        LocalDateTime localDateTime = DateUtil.plus(-(day * 24));
        XxlJobLogger.log("清除{}天以前", day);
        XxlJobLogger.log("清除截止日期：{}", DateUtil.localDateTimeToStr(localDateTime, DateUtil.FORMAT_A));

        XxlJobLogger.log("开始清除LogicalLog");
        long logicalLogCount = mongoDbService.remove(LogicalLog.class, () -> getQuery(localDateTime));
        XxlJobLogger.log("清除LogicalLog：{}条", logicalLogCount);
        XxlJobLogger.log("清除LogicalLog结束");

        XxlJobLogger.log("开始清除AccessLog");
        long accessLogCount = mongoDbService.remove(AccessLog.class, () -> getQuery(localDateTime));
        XxlJobLogger.log("清除AccessLog：{}条", accessLogCount);
        XxlJobLogger.log("清除AccessLog结束");

        return ReturnT.SUCCESS;
    }

    private Query getQuery(LocalDateTime localDateTime){
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.and("createDate").lt(localDateTime);
        query.addCriteria(criteria);
        return query;
    }
}
