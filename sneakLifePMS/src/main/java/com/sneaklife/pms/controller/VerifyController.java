package com.sneaklife.pms.controller;

import com.sneaklife.pms.service.verify.VerifyService;
import com.sneaklife.ut.code.FileCode;
import com.sneaklife.ut.common.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

@Controller
public class VerifyController {
    @Autowired
    private VerifyService verifyService;

    /**
     * 获取验证码图片url形式
     * @author yuanwei
     * @date 2018年10月29日
     * @version 1.0
     * @return
     */
    @RequestMapping(value = "/getVerifyImagesUrl", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    public ResponseEntity<String> getVerifyImagesUrl() {
        return verifyService.getVerifyImagesUrl();
    }

    /**
     * 获取验证码图片url二进制流
     * @author yuanwei
     * @date 2018年10月29日
     * @version 1.0
     */
    @ResponseBody
    @RequestMapping(value = "/getVerifyImagesStream", method = RequestMethod.POST, produces = "image/jpg")
    public void getVerifyImagesStream() {
        BufferedImage bufferedImage = verifyService.getVerifyImagesStream();
        try {
            HttpServletResponse response = CommonUtil.getResponse();
            response.setHeader("Access-Control-Allow-Origin", CommonUtil.getRequest().getHeader("Origin"));
            response.setHeader("Access-Control-Allow-Credentials", "true");
            OutputStream outputStream = response.getOutputStream();
            ImageIO.write(bufferedImage, FileCode.JPG.toValue(), outputStream);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 获取验证码图片urlbase64
     * @author yuanwei
     * @date 2018年10月29日
     * @version 1.0
     * @return
     */
    @RequestMapping(value = "/getVerifyImagesBase64", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    public ResponseEntity<String> getVerifyImagesBase64() {
        return verifyService.getVerifyImagesBase64();
    }

    /**
     *  获取手机验证码
     * @author yuanwei
     * @date 2018年10月29日
     * @version 1.0
     * @return
     */
    @RequestMapping(value = "/getSMS", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    public ResponseEntity<String> getSMS() {
        Map<String, Object> map = CommonUtil.getData(CommonUtil.getRequest());
        return verifyService.getSMS(map);
    }

    /**
     * 发送邮件
     * @author yuanwei
     * @date 2018年10月29日
     * @version 1.0
     * @param request
     * @return
     */
    @RequestMapping(value = "/sendMail", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    public ResponseEntity<String> sendMail(HttpServletRequest request) {
        Map<String, Object> map = CommonUtil.getData(request);
        return verifyService.sendMail(map);
    }

    /**
     * 验证邮件
     * @author yuanwei
     * @date 2018年10月29日
     * @version 1.0
     * @param request
     * @return
     */
    @RequestMapping(value = "/verifyMail", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    public ResponseEntity<String> verifyMail(HttpServletRequest request) {
        Map<String, Object> map = CommonUtil.getData(request);
        return verifyService.verifyMail(map);
    }
}
