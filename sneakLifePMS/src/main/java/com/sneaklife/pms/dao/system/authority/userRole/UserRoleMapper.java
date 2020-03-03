package com.sneaklife.pms.dao.system.authority.userRole;

import com.sneaklife.pms.dao.CommonDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/8/4 10:07
 */
@Mapper
public interface UserRoleMapper extends CommonDao {

    /**
     * 批量更新用户角色信息
     * @param list 更新数据
     * @return Integer
     */
    Integer updateBatch(List<Map<String, Object>> list);

}
