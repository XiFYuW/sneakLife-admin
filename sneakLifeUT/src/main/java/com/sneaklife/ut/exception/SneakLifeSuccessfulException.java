package com.sneaklife.ut.exception;

import org.springframework.http.ResponseEntity;

public class SneakLifeSuccessfulException extends SneakLifeException{

    public SneakLifeSuccessfulException(ResponseEntity<String> responseEntity) {
        super(responseEntity);
    }
}
