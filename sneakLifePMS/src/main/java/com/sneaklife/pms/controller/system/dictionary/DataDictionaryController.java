package com.sneaklife.pms.controller.system.dictionary;

import com.sneaklife.pms.service.system.dictionary.DataDictionaryService;
import com.sneaklife.ut.iws.IwsContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class DataDictionaryController {

    @Autowired
    private DataDictionaryService dataDictionaryService;

    @RequestMapping(value = "/insertDataDictionary", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public void insertDataDictionary(HttpServletRequest request) throws Exception{
        dataDictionaryService.insertDataDictionary(IwsContext.getData());
    }

    @RequestMapping(value = "/updateDataDictionary", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public void updateDataDictionary(HttpServletRequest request) throws Exception{
        dataDictionaryService.updateDataDictionary(IwsContext.getData());
    }

    @RequestMapping(value = "/deleteDataDictionary", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public void deleteDataDictionary(HttpServletRequest request) throws Exception{
        dataDictionaryService.deleteDataDictionary(IwsContext.getData());
    }

    @RequestMapping(value = "/getDataDictionary", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public ResponseEntity<String> getDataDictionary(HttpServletRequest request) throws Exception{
        return dataDictionaryService.getDataDictionary(IwsContext.getData(), IwsContext.getPageInfo());
    }

    @RequestMapping(value = "/dataDictionary", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public ResponseEntity<String> dataDictionary(HttpServletRequest request) throws Exception{
        return dataDictionaryService.dataDictionary(IwsContext.getData());
    }

    @RequestMapping(value = "/getByType", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public ResponseEntity<String> getByType(HttpServletRequest request) throws Exception{
        return dataDictionaryService.getByType(IwsContext.getData());
    }
}
