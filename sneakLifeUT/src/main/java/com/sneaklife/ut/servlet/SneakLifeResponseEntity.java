package com.sneaklife.ut.servlet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import java.io.Serializable;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/12/3 16:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SneakLifeResponseEntity<T> implements Serializable {

    private volatile Object responseEntity;
}
