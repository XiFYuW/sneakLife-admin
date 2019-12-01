package com.sneaklife.pms.service.common;

import org.springframework.http.ResponseEntity;

import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/12/1 12:27
 */
public interface SelectTreeViewService {

    ResponseEntity<String> selectTreeView(Map<String, Object> map);
}
