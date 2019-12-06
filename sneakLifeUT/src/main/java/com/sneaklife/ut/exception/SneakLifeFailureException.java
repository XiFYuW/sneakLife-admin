package com.sneaklife.ut.exception;

import org.springframework.http.ResponseEntity;

public class SneakLifeFailureException extends SneakLifeException{

    public SneakLifeFailureException(ResponseEntity<String> responseEntity) {
        super(responseEntity);
    }
}
