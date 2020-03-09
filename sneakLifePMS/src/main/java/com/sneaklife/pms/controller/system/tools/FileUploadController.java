package com.sneaklife.pms.controller.system.tools;

import com.sneaklife.pms.service.system.tools.FileUploadService;
import com.sneaklife.ut.iws.IwsContext;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/3/9 9:56
 */
@Controller
public class FileUploadController {

    private final FileUploadService fileUploadService;

    public FileUploadController(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }

    @RequestMapping(value = "/fileUpload", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntity<String> importToExcel(@RequestParam(value = "file_data", required = false) MultipartFile[] file,
                                                HttpServletRequest request, HttpServletResponse response) throws Exception {
        IwsContext.getSneakLifeServletObject(request,response);
        return IwsContext.respResultBodyToSC(fileUploadService.fileUpload(file));
    }
}
