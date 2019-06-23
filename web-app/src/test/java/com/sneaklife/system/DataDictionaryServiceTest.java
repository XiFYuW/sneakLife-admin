package com.sneaklife.system;

import com.sneaklife.ClientApplication;
import com.sneaklife.service.system.dictionary.DataDictionaryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ClientApplication.class)
@ContextConfiguration
public class DataDictionaryServiceTest {

    @Autowired
    private DataDictionaryService dataDictionaryService;

    @Test
    public void insertDataDictionary(){
        Map<String,Object> map = new HashMap<>();
        map.put("name","123");
        map.put("type","1");
        map.put("value","123456789");
        dataDictionaryService.insertDataDictionary(map);
    }

    @Test
    public void getDataDictionary(){
        dataDictionaryService.getDataDictionary(null);
    }

    @Test
    public void dataDictionary(){
        dataDictionaryService.dataDictionary(null);
    }

}
