package com.sneaklife.pms.controller.system.dictionary;

import com.sneaklife.pms.service.system.dictionary.DataDictionaryService;
import com.sneaklife.ut.iws.IwsContext;
import com.sneaklife.ut.log.AccessLogAn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DataDictionaryController {

    private final DataDictionaryService dataDictionaryService;

    @Autowired
    public DataDictionaryController(DataDictionaryService dataDictionaryService) {
        this.dataDictionaryService = dataDictionaryService;
    }

    @RequestMapping(value = "/insertDataDictionary", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    @AccessLogAn("/insertDataDictionary")
    public void insertDataDictionary() throws Exception{
        dataDictionaryService.insert(IwsContext.getData());
    }

    @RequestMapping(value = "/updateDataDictionary", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    @AccessLogAn("/updateDataDictionary")
    public void updateDataDictionary() throws Exception{
        dataDictionaryService.update(IwsContext.getData());
    }

    @RequestMapping(value = "/deleteDataDictionary", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    @AccessLogAn("/deleteDataDictionary")
    public void deleteDataDictionary() throws Exception{
        dataDictionaryService.delete(IwsContext.getData());
    }

    @RequestMapping(value = "/getDataDictionary", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    @AccessLogAn("/getDataDictionary")
    public ResponseEntity<String> getDataDictionary() throws Exception{
        return IwsContext.respResultBodyToSC(dataDictionaryService.getData(IwsContext.getData(), IwsContext.getPageInfo()));
    }

    @RequestMapping(value = "/dataDictionary", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    @AccessLogAn("/dataDictionary")
    public ResponseEntity<String> dataDictionary() throws Exception{
        return IwsContext.respResultBodyToSC(dataDictionaryService.buildData(IwsContext.getData()));
    }

    @RequestMapping(value = "/getByType", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    @AccessLogAn("/getByType")
    public ResponseEntity<String> getByType(){
        return IwsContext.respResultBodyToSC(dataDictionaryService.getByType(IwsContext.getData()));
    }

    @ResponseBody
    @RequestMapping(value = "/exportDataDictionary", method = RequestMethod.GET, produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8")
    @AccessLogAn("/exportDataDictionary")
    public void exportDataDictionary() throws Exception{
        dataDictionaryService.exportDataDictionary();
    }
}
