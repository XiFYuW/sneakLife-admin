package com.sneaklife.pms;

import com.sneaklife.SneakLifeAdminApplication;
import com.sneaklife.pms.service.system.dictionary.DataDictionaryService;
import com.sneaklife.pms.service.system.log.LogicalLogService;
import com.sneaklife.ut.page.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SneakLifeAdminApplication.class)
@ContextConfiguration
public class LogicalLogServiceTest {

    @Autowired
    private LogicalLogService logicalLogService;

    @Test
    public void getData() throws Exception{
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPage(1);
        pageInfo.setRows(10);
        pageInfo.setSort("id");
        pageInfo.setSortOrder("");
        Map<String,Object> map = new HashMap<>();
        map.put("sessionId", "7cf3d743-2972-4316-b508-ec435051d8de");
        Map<String,Object> data = logicalLogService.getData(map, pageInfo);
    }

}
