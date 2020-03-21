package com.sneaklife.pms.service.system.tools;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/3/9 9:58
 */
public interface FileUploadService {

   /**
    * 文件上传实例
    * @param file 文件对象信息
    * @return Map<String,Object>
    * @throws Exception 异常信息
    */
   Map<String,Object> fileUpload(MultipartFile[] file, Map<String,Object> map) throws Exception;
}
