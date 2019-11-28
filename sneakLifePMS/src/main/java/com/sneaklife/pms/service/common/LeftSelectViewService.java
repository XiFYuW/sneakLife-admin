package com.sneaklife.pms.service.common;

import org.springframework.http.ResponseEntity;

import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/11/28 12:14
 */
public interface LeftSelectViewService {

    ResponseEntity<String> leftSelectsView(Map<String, Object> map);
}
