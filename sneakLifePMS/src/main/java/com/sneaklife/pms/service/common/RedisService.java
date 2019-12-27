package com.sneaklife.pms.service.common;

import java.util.List;
import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/12/27 14:02
 */
public interface RedisService {

    /**
     * The user information is stored in redis
     * @param map parameter
     * @throws Exception
     */
    void setLoginUserInfo(Map<String, Object> map) throws Exception;

    /**
     * Get the login user function
     * @return List<String>
     * @throws Exception
     */
    List<String> getLoginUserOpera() throws Exception;

    /**
     * Get the cache key
     * @return String
     * @throws Exception
     */
    String getCacheId() throws Exception;
}
