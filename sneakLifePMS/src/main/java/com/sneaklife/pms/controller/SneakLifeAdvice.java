package com.sneaklife.pms.controller;

import com.sneaklife.ut.exception.SneakLifeException;
import com.sneaklife.ut.exception.ThrowableUtil;
import com.sneaklife.ut.iws.IwsContext;
import com.sneaklife.ut.iws.RespCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class SneakLifeAdvice {

    @ExceptionHandler(value = SneakLifeException.class)
    public ResponseEntity<String> errorHandlerSneakLifeException(SneakLifeException sneakLifeException) {
        log.info(ThrowableUtil.getStackTrace(sneakLifeException));
        return sneakLifeException.getResponseEntity();
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<String> errorHandlerSneakLifeException(Exception exception) {
        exception.printStackTrace();
        log.error(ThrowableUtil.getStackTrace(exception));
        return IwsContext.respResultBody(RespCode.MSG_XTYC.toValue(),RespCode.MSG_XTYC.toMsg());
    }

    @ExceptionHandler(value = Throwable.class)
    public ResponseEntity<String> errorHandlerSneakLifeException(Throwable throwable) {
        throwable.printStackTrace();
        log.error(ThrowableUtil.getStackTrace(throwable));
        return IwsContext.respResultBody(RespCode.MSG_XTYC.toValue(),RespCode.MSG_XTYC.toMsg());
    }
}
