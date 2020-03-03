package com.sneaklife.pms.dao.system.authority.opera;

import com.sneaklife.pms.dao.CommonDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 */
@Mapper
public interface OperaInMapper extends CommonDao {

    /**
     * 查询功能输入
     * @param map 功能提交
     * @return List<Map<String,Object>>
     */
    List<Map<String,Object>> findOperaIn(Map<String, Object> map);

    /**
     * 获取htmlType类型数据
     * @param menuId 菜单id
     * @param htmlType html类型
     * @return List<Map<String,Object>>
     */
    List<Map<String,Object>> getSelectsKyByMenuId(@Param("menuId") String menuId,@Param("htmlType") String htmlType);

    /**
     * 检测输入字段是否有规则
     * @param checkInId 输入字段检查id
     * @return List<Map<String,Object>>
     */
    List<Map<String,Object>> findByCheckInId(@Param("checkInId") String checkInId);
}
