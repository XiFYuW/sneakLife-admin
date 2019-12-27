package com.sneaklife.config.cache;

import org.springframework.cache.annotation.CacheEvict;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@CacheEvict(value = "SneakLifeAuthorityManagement", beforeInvocation=true, allEntries = true)
public @interface SneakLifeAuthorityManagementCacheEvict {
}
