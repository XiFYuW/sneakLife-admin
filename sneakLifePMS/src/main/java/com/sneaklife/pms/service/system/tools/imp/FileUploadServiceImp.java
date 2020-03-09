package com.sneaklife.pms.service.system.tools.imp;

import com.sneaklife.pms.service.system.tools.FileUploadService;
import com.sneaklife.ut.file.FileUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/3/9 9:58
 */
@Service
public class FileUploadServiceImp implements FileUploadService {

    @Override
    public Map<String,Object> fileUpload(MultipartFile[] file) throws Exception {
        for (MultipartFile multipartFile : file) {
            if(!(multipartFile.getSize() == 0L && "".equals(multipartFile.getOriginalFilename()))) {
                FileUtil.upload(multipartFile);
            }
        }
        return new HashMap<>();
    }
}
