package com.sneaklife.service.system;

import org.springframework.http.ResponseEntity;

public interface SystemMenuService {

    ResponseEntity<String> getMenu();
}
