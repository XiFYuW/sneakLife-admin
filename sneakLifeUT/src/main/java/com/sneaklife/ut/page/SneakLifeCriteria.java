package com.sneaklife.ut.page;

import org.springframework.data.mongodb.core.query.Query;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/1/27 13:27
 */
public interface SneakLifeCriteria {

    Query where() throws Exception;
}
