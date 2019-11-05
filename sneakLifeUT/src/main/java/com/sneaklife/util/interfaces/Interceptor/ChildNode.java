package com.sneaklife.util.interfaces.Interceptor;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface ChildNode {

    String itemUrl() default "#";
}
