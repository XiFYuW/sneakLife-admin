package com.sneaklife.controller;

import com.sneaklife.common.CommonUtil;
import com.sneaklife.exception.SneakLifeException;
import com.sneaklife.resp.RespCode;
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
        return CommonUtil.respResult(RespCode.MSG_XTYC.toValue(),RespCode.MSG_XTYC.toMsg());
    }
}
