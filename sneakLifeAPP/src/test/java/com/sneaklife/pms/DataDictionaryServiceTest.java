package com.sneaklife.pms;

import com.sneaklife.pms.service.system.dictionary.DataDictionaryService;
import com.sneaklife.SneakLifeAdminApplication;
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
public class DataDictionaryServiceTest {

    @Autowired
    private DataDictionaryService dataDictionaryService;

    @Test
    public void insertDataDictionary() throws Exception{
        Map<String,Object> map = new HashMap<>();
        map.put("name","button");
        map.put("type","32");
        map.put("value","0");
        dataDictionaryService.insertDataDictionary(map);
    }

    @Test
    public void getDataDictionary() throws Exception{
        dataDictionaryService.getDataDictionary(null, null);
    }

    @Test
    public void dataDictionary() throws Exception{
        dataDictionaryService.dataDictionary(null);
    }

}
