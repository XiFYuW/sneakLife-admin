package com.sneaklife.pms.service.common.imp;

import com.mongodb.client.result.DeleteResult;
import com.sneaklife.pms.service.common.CommonService;
import com.sneaklife.pms.service.common.MongoDbService;
import com.sneaklife.ut.page.PageInfo;
import com.sneaklife.ut.page.SneakLifeCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/2/3 10:13
 */
@Service
public class MongoDbServiceImp extends CommonService implements MongoDbService {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public MongoDbServiceImp(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public <T> Map<String, Object> getPageData(PageInfo pageInfo, Class<T> entityClass, SneakLifeCriteria sneakLifeCriteria) throws Exception {
        Query query = sneakLifeCriteria.where();
        Pageable pageable = getPageable(pageInfo);
        List<T> list = mongoTemplate.find(query.with(Objects.requireNonNull(pageable)), entityClass);
        org.springframework.data.domain.Page page = PageableExecutionUtils.getPage(list, pageable,
                () -> mongoTemplate.count(query, entityClass));
        return pageToMap(page);
    }

    @Override
    public <T> long remove(Class<T> entityClass, SneakLifeCriteria sneakLifeCriteria) throws Exception {
        Query query = sneakLifeCriteria.where();
        DeleteResult deleteResult = mongoTemplate.remove(query, entityClass);
        long count = deleteResult.getDeletedCount();
        if (count <= 0) {
            throw new IllegalArgumentException("删除失败");
        }
        return count;
    }
}
