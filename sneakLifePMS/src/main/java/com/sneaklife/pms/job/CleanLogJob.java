package com.sneaklife.pms.job;

import com.sneaklife.pms.service.common.CommonService;
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
public class CleanLogJob extends CommonService {

    private final MongoDbService mongoDbService;

    private static final int DEFAULT_DAY = 30;

    @Autowired
    public CleanLogJob(MongoDbService mongoDbService) {
        this.mongoDbService = mongoDbService;
    }

    @XxlJob("cleanLogJobHandler")
    public ReturnT<String> cleanLogJobHandler(String param) throws Exception {
        int day = DEFAULT_DAY;
        if (!StringUtil.isEmpty(param)) {
            day = Integer.valueOf(param);
        }
        String date = buildDate(day);
        XxlJobLogger.log("清除目标天数：{}", day);
        XxlJobLogger.log("清除日期范围：{}", date);

        XxlJobLogger.log("开始清除LogicalLog");
        mongoDbService.remove(LogicalLog.class, () -> {
            Query query = new Query();
            Criteria criteria = checkDataRange(date, new Criteria());
            query.addCriteria(criteria);
            return query;
        });
        XxlJobLogger.log("清除LogicalLog结束");

        XxlJobLogger.log("开始清除AccessLog");
        mongoDbService.remove(AccessLog.class, () -> {
            Query query = new Query();
            Criteria criteria = checkDataRange(date, new Criteria());
            query.addCriteria(criteria);
            return query;
        });
        XxlJobLogger.log("清除AccessLog结束");

        return ReturnT.SUCCESS;
    }

    private String buildDate(int day){
        String format = "yyyy-MM-dd HH:mm:ss";
        LocalDateTime localDateTime = LocalDateTime.now();
        String start = DateUtil.localDateTimeToStr(localDateTime, format);
        LocalDateTime localDateTime1 = localDateTime.plusHours(-(day * 24));
        String end = DateUtil.localDateTimeToStr(localDateTime1, format);
        return end + "," + start;
    }
}
