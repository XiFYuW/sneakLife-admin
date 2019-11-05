package com.sneaklife.util.redis.Interceptor;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface InsertCache {

	/**
	 * 表名
	 */
	String[] TABLES() default "";

	/**
	 * =false 不从HttpServletRequest获取key =true 从HttpServletRequest获取key
	 */
	boolean insertTo() default false;
}
