package com.sneaklife.pms.config.log;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface SneakLifeAnLog {
    String value() default "";
}
