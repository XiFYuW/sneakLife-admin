package com.sneaklife.controller.system.dictionary;

import com.sneaklife.common.CommonUtil;
import com.sneaklife.service.system.dictionary.DataDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class DataDictionaryController {

    @Autowired
    private DataDictionaryService dataDictionaryService;

    @RequestMapping(value = "/insertDataDictionary", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public ResponseEntity<String> saveDataDictionary(Map<String,Object> map){
        return dataDictionaryService.insertDataDictionary(map);
    }

    @RequestMapping(value = "/getDataDictionary", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public ResponseEntity<String> getDataDictionary(HttpServletRequest request){
        return dataDictionaryService.getDataDictionary(CommonUtil.getData(request), CommonUtil.getPageInfo(request));
    }

    @RequestMapping(value = "/dataDictionary", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public ResponseEntity<String> dataDictionary(HttpServletRequest request){
        return dataDictionaryService.dataDictionary(CommonUtil.getData(request));
    }
}
