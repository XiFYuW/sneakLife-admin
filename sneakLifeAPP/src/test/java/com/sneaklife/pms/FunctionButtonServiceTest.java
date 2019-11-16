package com.sneaklife.pms;

import com.sneaklife.pms.service.system.business.businessFunction.FunctionButtonService;
import com.sneaklife.SneakLifeAdminApplication;
import com.sneaklife.ut.code.page.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/8/22 10:34
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SneakLifeAdminApplication.class)
@ContextConfiguration
public class FunctionButtonServiceTest {

    @Autowired
    private FunctionButtonService functionButtonService;

    @Test
    public void getFunctionButton() throws Exception {
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPage(1);
        pageInfo.setRows(10);
        pageInfo.setSort("id");
        pageInfo.setSortOrder("");
        Map<String,Object> map = new HashMap<>();
        map.put("menuId","493b7b82c25211e98f5680fa5b3a283a");
        functionButtonService.getFunctionButton(map,pageInfo);
    }
}
