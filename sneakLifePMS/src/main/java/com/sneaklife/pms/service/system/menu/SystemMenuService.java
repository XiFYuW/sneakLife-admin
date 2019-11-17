package com.sneaklife.pms.service.system.menu;

import org.springframework.http.ResponseEntity;

/**
 * @author https://github.com/XiFYuW
 */
public interface SystemMenuService {

    /**
     * Get mappers.mappers menu
     * @return ResponseEntity<String>
     */
    ResponseEntity<String> getMenu();
}
