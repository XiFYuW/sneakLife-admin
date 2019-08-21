package com.sneaklife.redis.Interceptor;

import com.alibaba.fastjson.JSON;
import com.sneaklife.common.CommonUtil;
import com.sneaklife.constants.Constants;
import com.sneaklife.page.PageInfo;
import com.sneaklife.redis.RedisLoader;
import com.sneaklife.redis.RedisUtil;
import com.sneaklife.resp.RespCode;
import com.sneaklife.string.StringUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Set;

@Aspect
@Component
public class RedisCacheInterceptor {

	private final static RedisUtil REDISUTIL = RedisLoader.load();
	private static Logger log = LoggerFactory.getLogger(RedisCacheInterceptor.class);
	private String respCode = "respCode";
	/**
	 * 插入redis缓存的切面
	 * @author yuanwei
	 * @date 2018年10月29日
	 * @version 1.0
	 * @param pjp 切点
	 * @param insertCache 插入redis缓存的注解
	 * @return 响应实体
	 */
	@SuppressWarnings("unchecked")
	@Around("@annotation(com.sneaklife.redis.Interceptor.InsertCache) && @annotation(insertCache)")
	public ResponseEntity<String> insertCacheAround(ProceedingJoinPoint pjp, InsertCache insertCache) {
		Object[] objects = pjp.getArgs();
		String cacheKey = getKey(pjp, insertCache, objects);
		if (REDISUTIL.exists(REDISUTIL.getRedisTemplate(), cacheKey)) {
			Object data = REDISUTIL.get(REDISUTIL.getRedisTemplate(), cacheKey);
			log.info("从缓存中获取：{}", data);
			return CommonUtil.respResult(RespCode.MSG_SUCCEED.toValue(), data);
		}
		Object object = null;
		try {
			object = pjp.proceed(objects);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResponseEntity<String> responseEntity = (ResponseEntity<String>) object;
		String body = responseEntity.getBody();
		Map<String, Object> map = (Map<String, Object>) JSON.parse(body);
		Object data = map.get("respData");
		REDISUTIL.set(REDISUTIL.getRedisTemplate(), cacheKey, data, Constants.DATA_CACHE_TIMES);
		String table = StringUtil.disposeStrArray(insertCache.TABLES(), Constants.ARRAY_PARTITION);
		REDISUTIL.putHash(REDISUTIL.getRedisTemplate1(), Constants.TABLE_TREE, table, cacheKey, Constants.LOGIN_TIMES);
		return responseEntity;
	}

	/**
	 * 更新redis缓存的切面
	 * @author yuanwei
	 * @date 2018年10月29日
	 * @version 1.0
	 * @param pjp 切点
	 * @param updateCache 更新redis缓存注解
	 */
	@SuppressWarnings("unchecked")
	@Around("@annotation(com.sneaklife.redis.Interceptor.UpdateCache) && @annotation(updateCache)")
	public ResponseEntity<String> updateCacheAround(ProceedingJoinPoint pjp, UpdateCache updateCache) {
		Object[] objects = pjp.getArgs();
		Object object = null;
		try {
			object = pjp.proceed(objects);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResponseEntity<String> responseEntity = (ResponseEntity<String>) object;
		String body = responseEntity.getBody();
		Map<String, Object> map = (Map<String, Object>) JSON.parse(body);
		if (map.get(respCode).equals(RespCode.MSG_SUCCEED.toValue())) {
			String table = StringUtil.disposeStrArray(updateCache.TABLES(), Constants.ARRAY_PARTITION);
			int tableLen = table.length();
			Set<String> set = REDISUTIL.hashKeys(REDISUTIL.getRedisTemplate1(), Constants.TABLE_TREE);
			for (String key : set) {
				int keyLen = key.length();
				if (tableLen >= keyLen) {
					if (table.indexOf(key) != -1) {
						deleteCache(key);
					}
				} else {
					if (key.indexOf(table) != -1) {
						deleteCache(key);
					}
				}
			}
		}
		return responseEntity;
	}

	/**
	 * 删除缓存的key
	 * @author yuanwei
	 * @date 2018年10月29日
	 * @version 1.0
	 * @param key redis缓存的key
	 */
	private void deleteCache(String key) {
		Object object = REDISUTIL.hashGet(REDISUTIL.getRedisTemplate1(), Constants.TABLE_TREE, key);
		REDISUTIL.removePattern(REDISUTIL.getRedisTemplate(), String.valueOf(object));
		log.info("删除成功：{}", object);
		REDISUTIL.delHash(REDISUTIL.getRedisTemplate1(), Constants.TABLE_TREE, key);
		log.info("删除成功：{}", key);
	}

	/**
	 * 获取缓存key
	 * @author yuanwei
	 * @date 2018年10月29日
	 * @version 1.0
	 * @param pjp 切点
	 * @param insertCache 插入redis缓存的注解
	 * @param objects 目标方法的参数
	 * @return 缓存key
	 */
	private String getKey(ProceedingJoinPoint pjp, InsertCache insertCache, Object[] objects) {
		String hashCodeMethod = String.valueOf(pjp.getSignature().getName().hashCode());
		StringBuffer keys = new StringBuffer();
		if (!insertCache.insertTo()) {
			for (Object object : objects) {
				keys.append(object);
			}
		} else {
			HttpServletRequest request = (HttpServletRequest) objects[0];
			PageInfo pageInfo = (PageInfo) request.getAttribute("pag");
			Map<String, Object> map = CommonUtil.getData(request);
			if (pageInfo == null && map == null) {
				keys.append(CommonUtil.getTempUuid());
			}
			if (CommonUtil.isNull(pageInfo)) {
				keys.append(pageInfo);
			}
			if (CommonUtil.isNull(map)) {
				keys.append(map);
			}
		}
		keys.append(CommonUtil.getSessionValue(Constants.USERS_ROLE_TEMP, String.class));
		return CommonUtil.digest("CACHE_&" + hashCodeMethod + "_&" + keys.hashCode(), "SHA-1");
	}
}
