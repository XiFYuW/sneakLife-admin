package com.sneaklife.pms.service.system;

import org.springframework.http.ResponseEntity;

/**
 * @author https://github.com/XiFYuW
 */
public interface SystemMenuService {

    /**
     * Get pms menu
     * @return ResponseEntity<String>
     */
    ResponseEntity<String> getMenu();
}
