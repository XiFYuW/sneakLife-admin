package com.sneaklife.ut.file;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONObject;
import com.sneaklife.ut.date.DateUtil;
import com.sneaklife.ut.iws.IwsContext;
import com.sneaklife.ut.keyless.Base64Util;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.MimetypesFileTypeMap;
import java.io.*;
import java.security.MessageDigest;
import java.text.DecimalFormat;
/**
 * @author https://github.com/XiFYuW
 * @date 2020/1/28 11:47
 */
public class FileUtil {
    /**
     * 定义GB的计算常量
     */
    private static final int GB = 1024 * 1024 * 1024;
    /**
     * 定义MB的计算常量
     */
    private static final int MB = 1024 * 1024;
    /**
     * 定义KB的计算常量
     */
    private static final int KB = 1024;

    /**
     * 格式化小数
     */
    private static final DecimalFormat DF = new DecimalFormat("0.00");

    /**
     * MultipartFile转File
     */
    public static File toFile(MultipartFile multipartFile){
        // 获取文件名
        String fileName = multipartFile.getOriginalFilename();
        // 获取文件后缀
        String prefix="."+getExtensionName(fileName);
        File file = null;
        try {
            // 用uuid作为文件名，防止生成的临时文件重复
            file = File.createTempFile(IdUtil.simpleUUID(), prefix);
            // MultipartFile to File
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    /**
     * 获取文件扩展名，不带 .
     */
    public static String getExtensionName(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot >-1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
        }
        return filename;
    }

    /**
     * 获取不带扩展名的文件名
     */
    public static String getFileNameNoEx(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot >-1) && (dot < (filename.length()))) {
                return filename.substring(0, dot);
            }
        }
        return filename;
    }

    /**
     * 文件大小转换
     */
    public static String getSize(long size){
        String resultSize;
        if (size / GB >= 1) {
            //如果当前Byte的值大于等于1GB
            resultSize = DF.format(size / (float) GB) + "GB   ";
        } else if (size / MB >= 1) {
            //如果当前Byte的值大于等于1MB
            resultSize = DF.format(size / (float) MB) + "MB   ";
        } else if (size / KB >= 1) {
            //如果当前Byte的值大于等于1KB
            resultSize = DF.format(size / (float) KB) + "KB   ";
        } else {
            resultSize = size + "B   ";
        }
        return resultSize;
    }

    /**
     * inputStream 转 File
     */
    public static File inputStreamToFile(InputStream ins, String name) throws Exception{
        File file = new File(System.getProperty("java.io.tmpdir") + File.separator + name);
        if (file.exists()) {
            return file;
        }
        OutputStream os = new FileOutputStream(file);
        int bytesRead;
        byte[] buffer = new byte[8192];
        while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
            os.write(buffer, 0, bytesRead);
        }
        os.close();
        ins.close();
        return file;
    }

    /**
     * 判断文件路径是否以分隔符结尾
     * @param path 文件路径
     * @return String
     */
    public static String isPathEndsWith(String path){
        if (!path.endsWith(File.separator)) {
            return path + File.separator;
        }
        return path;
    }

    private static String createNameByDateTime(){
        return "-" + DateUtil.localDateTimeToStr(DateUtil.getNow(), "yyyyMMddHHmmssS");
    }

    /**
     * 保存文件至服务
     * @param file file参数
     */
    public static void upload(MultipartFile file) throws IOException {
        String filePath =  IwsContext.getSneakLifeServletObject().getServletContextRealPath("upload");
        String name = getFileNameNoEx(file.getOriginalFilename());
        String suffix = getExtensionName(file.getOriginalFilename());
        String nowStr = createNameByDateTime();
        String fileName = name + nowStr + "." + suffix;
        String path = isPathEndsWith(filePath) + fileName;
        File dest = createFile(path);
        file.transferTo(dest);
    }

    public static String fileToBase64(File file) throws Exception {
        FileInputStream inputFile = new FileInputStream(file);
        String base64;
        byte[] buffer = new byte[(int)file.length()];
        inputFile.read(buffer);
        inputFile.close();
        base64 = Base64Util.base64Encode(buffer);
        return base64.replaceAll("[\\s*\t\n\r]", "");
    }

    public static String getFileType(String type) {
        String documents = "txt doc pdf ppt pps xlsx xls docx";
        String music = "mp3 wav wma mpa ram ra aac aif m4a";
        String video = "avi mpg mpe mpeg asf wmv mov qt rm mp4 flv m4v webm ogv ogg";
        String image = "bmp dib pcp dif wmf gif jpg tif eps psd cdr iff tga pcd mpt png jpeg";
        if(image.contains(type)){
            return "图片";
        } else if(documents.contains(type)){
            return "文档";
        } else if(music.contains(type)){
            return "音乐";
        } else if(video.contains(type)){
            return "视频";
        } else {
            return "其他";
        }
    }

    public static String getFileTypeByMimeType(String type) {
        String mimeType = new MimetypesFileTypeMap().getContentType("." + type);
        return mimeType.split("/")[0];
    }

    public static void checkSize(long maxSize, long size) {
        if(size > (maxSize * 1024 * 1024)){
            throw new IllegalArgumentException("文件超出规定大小");
        }
    }

    /**
     * 判断两个文件是否相同
     */
    public static boolean check(File file1, File file2) {
        String img1Md5 = getMd5(file1);
        String img2Md5 = getMd5(file2);
        return img1Md5.equals(img2Md5);
    }

    /**
     * 判断两个文件是否相同
     */
    public static boolean check(String file1Md5, String file2Md5) {
        return file1Md5.equals(file2Md5);
    }

    private static byte[] getByte(File file) {
        // 得到文件长度
        byte[] b = new byte[(int) file.length()];
        try {
            InputStream in = new FileInputStream(file);
            try {
                in.read(b);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return b;
    }

    private static String getMd5(byte[] bytes) {
        // 16进制字符
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(bytes);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;
            // 移位 输出字符串
            for (byte byte0 : md) {
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getMd5(File file) {
        return getMd5(getByte(file));
    }

    /**
     * 创建文件
     * @param path 文件路径
     * @return File
     * @throws IOException 异常信息
     */
    public static File createFile(String path) throws IOException {
        File file = new File(path);
        if (!file.getCanonicalFile().getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        return file;
    }

    public static void outFile(String path, Object object) throws IOException {
        File file = FileUtil.createFile(path);
        PrintStream ps = new PrintStream(new FileOutputStream(file));
        ps.println(JSONObject.toJSONString(object));
        ps.flush();
        ps.close();
    }
}
