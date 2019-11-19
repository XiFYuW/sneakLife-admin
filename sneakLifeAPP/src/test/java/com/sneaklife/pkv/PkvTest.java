package com.sneaklife.pkv;

import com.sneaklife.SneakLifeAdminApplication;
import com.sneaklife.ut.spring.SpringContextUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/11/17 18:07
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SneakLifeAdminApplication.class)
@ContextConfiguration
public class PkvTest {

    @Autowired
    private RsaPKV rsaPKV;

    @Autowired
    private AesPKV aesPKV;

    @Autowired
    private AilSmsPKV ailSmsPKV;

    @Test
    public void getRsaPkv(){
        System.out.println(rsaPKV.getKeyAlgorithm());
    }

    @Test
    public void getAesPkv(){
        System.out.println(aesPKV.getKeyAlgorithm());
    }

    @Test
    public void getAilSmsPkv(){
        System.out.println(ailSmsPKV.getDefaultConnectTimeout());
    }

    @Test
    public void getSpringContext(){
        RsaPKV rsaPKV = SpringContextUtil.getBean(RsaPKV.class);
        System.out.println(rsaPKV.getKeyAlgorithm());
    }
}
