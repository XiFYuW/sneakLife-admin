package com.sneaklife.ut.log;

import java.lang.annotation.*;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/12/8 11:52
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface AccessLogAn {
    String value() default "";
}
