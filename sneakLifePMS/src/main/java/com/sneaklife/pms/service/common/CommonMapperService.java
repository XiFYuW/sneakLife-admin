package com.sneaklife.pms.service.common;

import com.sneaklife.pms.entity.TableOpera;
import com.sneaklife.ut.page.PageInfo;

import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/1/26 12:50
 */
public interface CommonMapperService {

    /**
     * 添加一条数据
     * @param map 数据项
     * @throws Exception 成功失败提示信息
     */
    void insert(Map<String, Object> map) throws Exception;

    /**
     * 获取带分页信息的数据
     * @param map 条件信息
     * @param pageInfo 分页信息
     * @return Map 查询分页信息之后的数据，以“content”为数据，以“totalElements”为总数量
     * @throws Exception 异常信息提示
     */
    Map<String,Object> getData(Map<String, Object> map, PageInfo pageInfo) throws Exception;

    /**
     * 构建页面主体信息，包括：‘页面字段’、‘功能按钮’、‘输入字段’、‘查询字段’
     * @param map 参数条件
     * @return TableOpera
     * @throws Exception 异常信息提示
     */
    TableOpera buildData(Map<String, Object> map) throws Exception;

    /**
     * 更新一条数据
     * @param map 数据项
     * @throws Exception 异常信息提示
     */
    void update(Map<String, Object> map) throws Exception;

    /**
     * 批量删除数据
     * @param map 以“ids”为key，以逗号隔开的数据项为value
     * @throws Exception 异常信息提示
     */
    void delete(Map<String, Object> map) throws Exception;
}
