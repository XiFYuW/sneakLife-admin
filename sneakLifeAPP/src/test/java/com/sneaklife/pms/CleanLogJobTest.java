package com.sneaklife.pms;

import com.sneaklife.SneakLifeAdminApplication;
import com.sneaklife.pms.job.CleanLogJob;
import com.sneaklife.ut.date.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/2/3 10:47
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SneakLifeAdminApplication.class)
@ContextConfiguration
public class CleanLogJobTest {
    @Autowired
    CleanLogJob cleanLogJob;

    @Test
    public void clean(){
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(DateUtil.localDateTimeToStr(localDateTime, "yyyy-MM-dd HH:mm:ss"));
        LocalDateTime localDateTime1 = localDateTime.plusHours((30 * 24));
        System.out.println(DateUtil.localDateTimeToStr(localDateTime1, "yyyy-MM-dd HH:mm:ss"));
    }
}
