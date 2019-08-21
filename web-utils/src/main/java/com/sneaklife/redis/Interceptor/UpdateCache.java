package com.sneaklife.redis.Interceptor;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface UpdateCache {
	/**
	 * 表名
	 */
	String[] TABLES();
}
