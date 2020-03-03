package com.sneaklife.pms.service.common;

import com.sneaklife.ut.page.PageInfo;
import com.sneaklife.ut.interfaces.SneakLifeCriteria;

import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/2/3 10:12
 */
public interface MongoDbService {

    /**
     * 获取分页数据
     * @param pageInfo 分页信息
     * @param entityClass 实体类Class
     * @param sneakLifeCriteria 条件接口
     * @param <T> 实体类型
     * @return 查询分页信息之后的数据，以“content”为数据，以“totalElements”为总数量
     * @throws Exception 异常信息提示
     */
    <T> Map<String, Object> getPageData(PageInfo pageInfo, Class<T> entityClass, SneakLifeCriteria sneakLifeCriteria) throws Exception;

    /**
     * 删除数据
     * @param entityClass 实体类Class
     * @param sneakLifeCriteria 条件接口
     * @param <T> 实体类型
     * @return 删除数量
     * @throws Exception 异常信息提示
     */
    <T> long remove(Class<T> entityClass, SneakLifeCriteria sneakLifeCriteria) throws Exception;
}
