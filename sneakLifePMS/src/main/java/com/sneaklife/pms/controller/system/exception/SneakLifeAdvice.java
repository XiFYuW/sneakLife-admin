package com.sneaklife.pms.controller.system.exception;

import com.sneaklife.ut.code.exception.SneakLifeException;
import com.sneaklife.ut.code.resp.RespCode;
import com.sneaklife.ut.common.CommonUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SneakLifeAdvice {

    @ExceptionHandler(value = SneakLifeException.class)
    public ResponseEntity<String> errorHandlerSneakLifeException(SneakLifeException sneakLifeException) {
        return sneakLifeException.getResponseEntity();
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<String> errorHandlerSneakLifeException(Exception exception) {
        exception.printStackTrace();
        return CommonUtil.respResult(RespCode.MSG_XTYC.toValue(),RespCode.MSG_XTYC.toMsg());
    }

    @ExceptionHandler(value = Throwable.class)
    public ResponseEntity<String> errorHandlerSneakLifeException(Throwable throwable) {
        throwable.printStackTrace();
        return CommonUtil.respResult(RespCode.MSG_XTYC.toValue(),RespCode.MSG_XTYC.toMsg());
    }
}
