package com.sneaklife.pms.service.common;

import com.sneaklife.ut.page.PageInfo;
import com.sneaklife.ut.page.SneakLifeCriteria;

import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/2/3 10:12
 */
public interface MongoDbService {

    <T> Map<String, Object> getPageData(PageInfo pageInfo, Class<T> entityClass, SneakLifeCriteria sneakLifeCriteria) throws Exception;

    <T> long remove(Class<T> entityClass, SneakLifeCriteria sneakLifeCriteria) throws Exception;
}
