package com.sneaklife.pms.service.common;

import java.util.List;
import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/12/27 14:02
 */
public interface RedisService {

    /**
     * 设置登录用户信息
     * @param map parameter 信息参数
     * @throws Exception 异常信息提示
     */
    void setLoginUserInfo(Map<String, Object> map) throws Exception;

    /**
     * 获取登录用户的功能
     * @return List<String> 登录用户的功能
     * @throws Exception 异常信息提示
     */
    List<String> getLoginUserOpera() throws Exception;

    /**
     * 获取功能菜单
     * @param map 条件参数
     * @return 功能菜单
     * @throws Exception 异常信息提示
     */
    List<String> getOpera(Map<String,Object> map) throws Exception;

    /**
     * 获取具体的功能选项
     * @param map 条件参数
     * @return 功能选项
     * @throws Exception 异常信息提示
     */
    List<String> getSpOpera(Map<String,Object> map) throws Exception;

    /**
     * 获取缓存key
     * @return String 缓存key
     * @throws Exception 异常信息提示
     */
    String getCacheId() throws Exception;

    void logOut() throws Exception;
}
