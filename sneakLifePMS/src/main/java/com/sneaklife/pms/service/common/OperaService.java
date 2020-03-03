package com.sneaklife.pms.service.common;

import com.sneaklife.pms.entity.TableOpera;

import java.util.List;
import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 */
public interface OperaService {

    /**
     * 构建页面
     * @param map 条件参数
     * @param is 是否指定长度进行切割
     * @return TableOpera对象
     * @throws Exception 异常信息提示
     */
    TableOpera buildOperaBody(Map<String, Object> map, boolean is) throws Exception;

    /**
     * 构建具体功能选项数据
     * @map 参数条件
     * @return 树形数据
     * @throws Exception 异常提示信息
     */
    List<Map<String, Object>> buildSpecificRoleFunction(Map<String, Object> map) throws Exception;

    /**
     * 更新具体功能
     * @param map 条件参数
     * @throws Exception 异常提示信息
     */
    void updateSpecificRoleFunction(Map<String, Object> map) throws Exception;

    /**
     * 更新功能菜单
     * @param map 条件参数
     * @throws Exception 异常提示信息
     */
    void updateRoleFunction(Map<String, Object> map) throws Exception;

    /**
     * 获取htmlType类型数据
     * @param menuId 功能菜单id
     * @param htmlType HTML类型
     * @return List<Map<String, Object>>
     */
    List<Map<String, Object>> getSelectsKyByMenuId(String menuId, String htmlType);

}
