package com.sneaklife.pms;

import com.sneaklife.SneakLifeAdminApplication;
import com.sneaklife.config.SneakLifeSystemEnum;
import com.sneaklife.ut.keyless.KeyLessContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.NoSuchAlgorithmException;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/12/26 14:48
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SneakLifeAdminApplication.class)
@ContextConfiguration
public class UserServiceTest {

    @Test
    public void pass() throws NoSuchAlgorithmException {
        String re = KeyLessContext.digest(SneakLifeSystemEnum.SNEAK_LIFE_NAME.toName() + "123456", "md5");
        System.out.println(re);
        System.out.println(re.length());
    }
}
