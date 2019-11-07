package com.sneaklife.pms.controller;

import com.sneaklife.ut.code.resp.RespCode;
import com.sneaklife.ut.common.CommonUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TokenController {
    /**
     * 初始化token
     * @author yuanwei
     * @date 2018年10月29日
     * @version 1.0
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/hysbptk", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public ResponseEntity<String> hysbptk() throws Exception {
        return CommonUtil.respResult(RespCode.MSG_CSTOKEN.toValue(), CommonUtil.sendToken());
    }

    /**
     * Token过期，重新获取
     * token overdue
     * @author yuanwei
     */
    @RequestMapping(value = "/to", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public ResponseEntity<String> to() throws Exception {
        return CommonUtil.respResult(RespCode.MSG_GQTOKEN.toValue(), CommonUtil.sendToken());
    }

    /**
     * 非法用户，请登录
     * illegal user
     */
    @RequestMapping(value = "/iu", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public ResponseEntity<String> iu() {
        return CommonUtil.respResult(RespCode.MSG_FFUSERS.toValue(), RespCode.MSG_FFUSERS.toMsg());
    }
    /**
     * 解密参数失败,请重试！
     * Decryption parameter failed
     * @author yuanwei
     */
    @RequestMapping(value = "/dpf", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public ResponseEntity<String> dpf() {
        return CommonUtil.respResult(RespCode.MSG_JMSBZS.toValue(), RespCode.MSG_JMSBZS.toMsg());
    }
    /**
     * 数字签名校验不通过
     * Data signature check
     * @author yuanwei
     */
    @RequestMapping(value = "/dsc", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public ResponseEntity<String> dsc() {
        return CommonUtil.respResult(RespCode.MSG_SZQMJYBTG.toValue(), RespCode.MSG_SZQMJYBTG.toMsg());
    }
}
