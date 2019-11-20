package com.sneaklife.ut.exception;

import org.springframework.http.ResponseEntity;

public class SneakLifeException extends Exception{

    private ResponseEntity<String> responseEntity;

    SneakLifeException(){}

    public SneakLifeException(ResponseEntity<String> responseEntity){
        this.responseEntity = responseEntity;
    }

    public ResponseEntity<String> getResponseEntity() {
        return responseEntity;
    }

    public void setResponseEntity(ResponseEntity<String> responseEntity) {
        this.responseEntity = responseEntity;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"responseEntity\":")
                .append(responseEntity);
        sb.append('}');
        return sb.toString();
    }
}
