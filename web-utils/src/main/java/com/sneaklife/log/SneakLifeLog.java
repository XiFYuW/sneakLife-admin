package com.sneaklife.log;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface SneakLifeLog {
    String value() default "";
}
