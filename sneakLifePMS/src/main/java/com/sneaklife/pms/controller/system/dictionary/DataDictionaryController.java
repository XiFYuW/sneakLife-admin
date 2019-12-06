package com.sneaklife.pms.controller.system.dictionary;

import com.sneaklife.pms.service.system.dictionary.DataDictionaryService;
import com.sneaklife.ut.exception.SneakLifeException;
import com.sneaklife.ut.iws.IwsContext;
import com.sneaklife.ut.servlet.SneakLifeResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
public class DataDictionaryController {

    @Autowired
    private DataDictionaryService dataDictionaryService;

    @RequestMapping(value = "/insertDataDictionary", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public void insertDataDictionary() throws Exception{
        dataDictionaryService.insertDataDictionary(IwsContext.getData());
    }

    @RequestMapping(value = "/updateDataDictionary", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public void updateDataDictionary() throws Exception{
        dataDictionaryService.updateDataDictionary(IwsContext.getData());
    }

    @RequestMapping(value = "/deleteDataDictionary", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public void deleteDataDictionary() throws Exception{
        dataDictionaryService.deleteDataDictionary(IwsContext.getData());
    }

    @RequestMapping(value = "/getDataDictionary", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public ResponseEntity<String> getDataDictionary() throws Exception{
        return IwsContext.respResultBodyToSC(dataDictionaryService.getDataDictionary(IwsContext.getData(), IwsContext.getPageInfo()));
    }

    @RequestMapping(value = "/dataDictionary", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public ResponseEntity<String> dataDictionary(){
        return IwsContext.respResultBodyToSC(dataDictionaryService.dataDictionary(IwsContext.getData()));
    }

    @RequestMapping(value = "/getByType", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public ResponseEntity<String> getByType(){
        return IwsContext.respResultBodyToSC(dataDictionaryService.getByType(IwsContext.getData()));
    }
}
