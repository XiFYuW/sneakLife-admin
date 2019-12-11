package com.sneaklife.pms.controller.system.dictionary;

import com.sneaklife.pms.service.system.dictionary.TypeDictionaryService;
import com.sneaklife.ut.iws.IwsContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TypeDictionaryController {

    @Autowired
    private TypeDictionaryService typeDictionaryService;

    @RequestMapping(value = "/insertTypeDictionary", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public void insertTypeDictionary() throws Exception{
        typeDictionaryService.insertTypeDictionary(IwsContext.getData());
    }

    @RequestMapping(value = "/updateTypeDictionary", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public void updateTypeDictionary() throws Exception{
        typeDictionaryService.updateTypeDictionary(IwsContext.getData());
    }

    @RequestMapping(value = "/deleteTypeDictionary", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public void deleteTypeDictionary() throws Exception{
        typeDictionaryService.deleteTypeDictionary(IwsContext.getData());
    }

    @RequestMapping(value = "/getTypeDictionary", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public ResponseEntity<String> getTypeDictionary() throws Exception{
        return IwsContext.respResultBodyToSC(typeDictionaryService.getTypeDictionary(IwsContext.getData(), IwsContext.getPageInfo()));
    }

    @RequestMapping(value = "/typeDictionary", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public ResponseEntity<String> typeDictionary(){
        return IwsContext.respResultBodyToSC(typeDictionaryService.typeDictionary(IwsContext.getData()));
    }

}