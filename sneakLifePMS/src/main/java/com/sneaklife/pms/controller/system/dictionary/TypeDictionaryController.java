package com.sneaklife.pms.controller.system.dictionary;

import com.sneaklife.pms.service.system.dictionary.TypeDictionaryService;
import com.sneaklife.ut.iws.IwsContext;
import com.sneaklife.ut.log.AccessLogAn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TypeDictionaryController {

    private final TypeDictionaryService typeDictionaryService;

    @Autowired
    public TypeDictionaryController(TypeDictionaryService typeDictionaryService) {
        this.typeDictionaryService = typeDictionaryService;
    }

    @RequestMapping(value = "/insertTypeDictionary", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    @AccessLogAn("/insertTypeDictionary")
    public void insertTypeDictionary() throws Exception{
        typeDictionaryService.insert(IwsContext.getData());
    }

    @RequestMapping(value = "/updateTypeDictionary", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    @AccessLogAn("/updateTypeDictionary")
    public void updateTypeDictionary() throws Exception{
        typeDictionaryService.update(IwsContext.getData());
    }

    @RequestMapping(value = "/deleteTypeDictionary", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    @AccessLogAn("/deleteTypeDictionary")
    public void deleteTypeDictionary() throws Exception{
        typeDictionaryService.delete(IwsContext.getData());
    }

    @RequestMapping(value = "/getTypeDictionary", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    @AccessLogAn("/getTypeDictionary")
    public ResponseEntity<String> getTypeDictionary() throws Exception{
        return IwsContext.respResultBodyToSC(typeDictionaryService.getData(IwsContext.getData(), IwsContext.getPageInfo()));
    }

    @RequestMapping(value = "/typeDictionary", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    @AccessLogAn("/typeDictionary")
    public ResponseEntity<String> typeDictionary() throws Exception{
        return IwsContext.respResultBodyToSC(typeDictionaryService.buildData(IwsContext.getData()));
    }

}
