package com.sneaklife.pms;

import com.sneaklife.SneakLifeAdminApplication;
import com.sneaklife.pms.service.common.SelectTreeViewService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/12/1 12:44
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SneakLifeAdminApplication.class)
@ContextConfiguration
public class SelectTreeViewServiceTest {

    @Autowired
    private SelectTreeViewService selectTreeViewService;

    @Test
    public void selectTreeView(){
        selectTreeViewService.selectTreeView(new HashMap<>());
    }
}
