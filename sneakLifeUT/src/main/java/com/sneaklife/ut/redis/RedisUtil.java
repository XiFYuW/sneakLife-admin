package com.sneaklife.ut.redis;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class RedisUtil {
	private static RedisTemplate<String, Object> redisTemplate;
	private static RedisTemplate<String, Object> redisTemplate1;
	private ValueOperations<String, Object> operations;
	private HashOperations<String, String, Object> opsForHash;
	public static void setRedisTemplate(RedisTemplate<String, Object> redisTemplate, RedisTemplate<String, Object> redisTemplate1){
		RedisUtil.redisTemplate = redisTemplate;
		RedisUtil.redisTemplate1 = redisTemplate1;
	}

	public RedisTemplate<String, Object> getRedisTemplate() {
		return redisTemplate;
	}

	public RedisTemplate<String, Object> getRedisTemplate1() {
		return redisTemplate1;
	}

	/**
	 * 批量删除对应的value
	 *
	 */
	public void remove(RedisTemplate<String, Object> redisTemplate, final String... keys) {
		for (String key : keys) {
			removePattern(redisTemplate, key);
		}
	}

	/**
	 * 批量删除key
	 */
	public void removePattern(RedisTemplate<String, Object> redisTemplate, final String pattern) {
		Set<String> keys = redisTemplate.keys(pattern);
		if (keys.size() > 0) {
			redisTemplate.delete(keys);
		}
	}

	/**
	 * 删除对应的value
	 */
	public void remove(RedisTemplate<String, Object> redisTemplate, final String key) {
		if (exists(redisTemplate, key)) {
			redisTemplate.delete(key);
		}
	}

	/**
	 * 判断缓存中是否有对应的value
	 */
	public boolean exists(RedisTemplate<String, Object> redisTemplate, final String key) {
		return redisTemplate.hasKey(key);
	}

	/**
	 * 读取缓存
	 */
	public Object get(RedisTemplate<String, Object> redisTemplate, final String key) {
		Object result = getValueOperations(redisTemplate).get(key);
		return result;
	}

	/**
	 * 写入缓存
	 */
	public void set(RedisTemplate<String, Object> redisTemplate, final String key, Object value) {
		getValueOperations(redisTemplate).set(key, value);
	}

	/**
	 * 写入缓存
	 */
	public void set(RedisTemplate<String, Object> redisTemplate, final String key, Object value, Long expireTime) {
		getValueOperations(redisTemplate).set(key, value);
		redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
	}

	/**
	 * hash操作
	 */
	public void putHash(RedisTemplate<String, Object> redisTemplate, final String key, String key1, Object object) {
		getHashOperations(redisTemplate).put(key, key1, object);
	}

	public void putAllHash(RedisTemplate<String, Object> redisTemplate, final String key, Map<String, Object> map) {
		getHashOperations(redisTemplate).putAll(key, map);
	}

	public void putHash(RedisTemplate<String, Object> redisTemplate, final String key, String key1, Object object,
			Long expireTime) {
		getHashOperations(redisTemplate).put(key, key1, object);
		redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
	}

	public void putAllHash(RedisTemplate<String, Object> redisTemplate, final String key, Map<String, Object> map,
			Long expireTime) {
		getHashOperations(redisTemplate).putAll(key, map);
		redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
	}

	public void delHash(RedisTemplate<String, Object> redisTemplate, final String key, String key1) {
		getHashOperations(redisTemplate).delete(key, key1);
	}

	public boolean isHashKey(RedisTemplate<String, Object> redisTemplate, final String key, String key1) {
		return getHashOperations(redisTemplate).hasKey(key, key1);
	}

	public Set<String> hashKeys(RedisTemplate<String, Object> redisTemplate, final String key) {
		return getHashOperations(redisTemplate).keys(key);
	}

	public List<Object> hashValues(RedisTemplate<String, Object> redisTemplate, final String key) {
		return getHashOperations(redisTemplate).values(key);
	}

	public long hashSize(RedisTemplate<String, Object> redisTemplate, final String key) {
		return getHashOperations(redisTemplate).size(key);
	}

	public Object hashGet(RedisTemplate<String, Object> redisTemplate, final String key, String key1) {
		return getHashOperations(redisTemplate).get(key, key1);
	}

	private ValueOperations<String, Object> getValueOperations(RedisTemplate<String, Object> redisTemplate) {
		if (this.operations == null) {
			return redisTemplate.opsForValue();
		}
		return this.operations;
	}

	private HashOperations<String, String, Object> getHashOperations(RedisTemplate<String, Object> redisTemplate) {
		if (this.opsForHash == null) {
			return redisTemplate.opsForHash();
		}
		return this.opsForHash;
	}
}
