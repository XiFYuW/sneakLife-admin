package com.sneaklife.pms.config.dao;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface SneakLifeAutoBo {
    String[] value() default {};
}
