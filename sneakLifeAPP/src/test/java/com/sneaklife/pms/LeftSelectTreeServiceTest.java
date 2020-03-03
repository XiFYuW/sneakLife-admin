package com.sneaklife.pms;

import com.sneaklife.SneakLifeAdminApplication;
import com.sneaklife.pms.service.common.SelectTreeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/2/10 10:41
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SneakLifeAdminApplication.class)
@ContextConfiguration
public class LeftSelectTreeServiceTest {

    @Resource(name = "leftSelectTreeServiceImp")
    private SelectTreeService selectTreeService;

    @Test
    public void selectTree() throws Exception {
        selectTreeService.selectTree(new HashMap<>());
    }
}
