package com.sneaklife.ut.interfaces;

import org.springframework.data.mongodb.core.query.Query;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/1/27 13:27
 */
public interface SneakLifeCriteria {

    /**
     * 执行JPA查询条件
     * @return Query对象
     * @throws Exception 异常信息提示
     */
    Query where() throws Exception;
}
