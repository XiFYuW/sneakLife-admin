package com.sneaklife.ut.redis;

public class RedisLoader {

	private static RedisLoader redisLoaderUtil = null;
	private final static RedisUtil REDISUTIL = new RedisUtil();

	public static RedisLoader getInstance() {
		if (redisLoaderUtil == null) {
			redisLoaderUtil = new RedisLoader();
		}
		return redisLoaderUtil;
	}

	private RedisUtil getRedisutil() {
		return REDISUTIL;
	}
	public static RedisUtil load() {
		return RedisLoader.getInstance().getRedisutil();
	}
}
