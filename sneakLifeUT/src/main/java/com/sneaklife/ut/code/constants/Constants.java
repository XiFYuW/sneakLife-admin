package com.sneaklife.ut.code.constants;

import com.sneaklife.ut.code.properties.ConfigProperties;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Constants {

	/**
	 * RSA
	 */
	public static final String KEY_ALGORITHM = ConfigProperties.getSystemConfig("KEY_ALGORITHM");
	/**
	 * RSA算法
	 */
	public static final String SIGNATURE_ALGORITHM = ConfigProperties.getSystemConfig("SIGNATURE_ALGORITHM");
	/**
	 * RSA公钥键
	 */
	public static final String PUBLIC_KEY = ConfigProperties.getSystemConfig("PUBLIC_KEY");
	/**
	 * RSA公钥键
	 */
	public static final String PRIVATE_KEY = ConfigProperties.getSystemConfig("PRIVATE_KEY");
	/**
	 * RSA签名
	 */
	public static final String SIGNA_NAME = ConfigProperties.getSystemConfig("SIGNA_NAME");
	/**
	 *
	 */
	public static final int MAX_ENCRYPT_BLOCK = Integer.valueOf(ConfigProperties.getSystemConfig("MAX_ENCRYPT_BLOCK"));
	/**
	 *
	 */
	public static final int MAX_DECRYPT_BLOCK = Integer.valueOf(ConfigProperties.getSystemConfig("MAX_DECRYPT_BLOCK"));
	/**
	 * 服务url
	 */
	public static final String SERVICE_URL = ConfigProperties.getSystemConfig("SERVICE_URL");
	/**
	 * 短信验证超时时间
	 */
	public static final long SMS_TIMES = Long.valueOf(ConfigProperties.getSystemConfig("SMS_TIMES"));
	/**
	 * 邮箱验证超时时间
	 */
	public static final long MAIL_TIMES = Long.valueOf(ConfigProperties.getSystemConfig("MAIL_TIMES"));
	/**
	 * 图片验证超时时间
	 */
	public static final long IMAGE_TIMES = Long.valueOf(ConfigProperties.getSystemConfig("IMAGE_TIMES"));
	/**
	 * 登录超时时间
	 */
	public static final long LOGIN_TIMES = Long.valueOf(ConfigProperties.getSystemConfig("LOGIN_TIMES"));
	/**
	 * 数组分割符
	 */
	public static final String ARRAY_PARTITION = ConfigProperties.getSystemConfig("ARRAY_PARTITION");
	/**
	 * 缓存键的树
	 */
	public static final String TABLE_TREE = ConfigProperties.getSystemConfig("TABLE_TREE");
	/**
	 * cache失效时间，40分钟
	 */
	public static final long DATA_CACHE_TIMES = Long.valueOf(ConfigProperties.getSystemConfig("DATA_CACHE_TIMES"));
	/**
	 * 管理员
	 */
	public static final String ROLE_ADMIN = "管理员";
	/**
	 * 登录用户数量
	 */
	public static final String USERS_SS_COUNT = ConfigProperties.getSystemConfig("USERS_SS_COUNT");
	/**
	 * 登录用户id
	 */
	public static final String USERS_ID_TEMP = ConfigProperties.getSystemConfig("USERS_ID_TEMP");
	/**
	 * 登录用户角色
	 */
	public static final String USERS_ROLE_TEMP = ConfigProperties.getSystemConfig("USERS_ROLE_TEMP");
	/**
	 * 邮箱验证
	 */
	public static final String MAILCODE = ConfigProperties.getSystemConfig("MAILCODE");
	/**
	 * 图片验证
	 */
	public static final String COOKVERIFY = ConfigProperties.getSystemConfig("COOKVERIFY");
	/**
	 * 手机验证
	 */
	public static final String SJYZM = ConfigProperties.getSystemConfig("SJYZM");
	/**
	 * 初始token
	 */
	public static String HYSBPT_V_YYY = ConfigProperties.getSystemConfig("HYSBPT_V_YYY");
	/**
	 * 加密算法
	 */
	public static final String ALGORITHMSTR = ConfigProperties.getSystemConfig("ALGORITHMSTR");
	/**
	 * token 缓存时间
	 */
	public static final long TOKEN_CACHE_TIMES = Long.valueOf(ConfigProperties.getSystemConfig("TOKEN_CACHE_TIMES"));
	/**
	 * token 缓存键
	 */
	public static final String TOKEN_KEY = ConfigProperties.getSystemConfig("TOKEN_KEY");
	/**
	 * 验证码请求路径
	 */
	public static final String SERVER_PATH_YZM = ConfigProperties.getSystemConfig("serverPathYzm");
	/**
	 * 验证码存放路径
	 */
	public static final String FILE_PATH_YZM = ConfigProperties.getSystemConfig("filePathYzm");
	/**
	 * 头像请求路径
	 */
	public static final String SERVER_PATH_PHO = ConfigProperties.getSystemConfig("serverPathPho");
	/**
	 * 头像存放路径
	 */
	public static final String FILE_PATH_PHO = ConfigProperties.getSystemConfig("filePathPho");
	/**
	 * 二维码字符
	 */
	public static final String QR_CHARSET = ConfigProperties.getSystemConfig("QR_CHARSET");
	/**
	 * 二维码格式
	 */
	public static final String QR_FORMAT = ConfigProperties.getSystemConfig("QR_FORMAT");
	/**
	 * 二维码尺寸
	 */
	public static final int QR_CODE_SIZE = Integer.valueOf(ConfigProperties.getSystemConfig("QR_CODE_SIZE"));
	/**
	 * 二维码logo宽度
	 */
	public static final int QR_LOGO_WIDTH = Integer.valueOf(ConfigProperties.getSystemConfig("QR_LOGO_WIDTH"));
	/**
	 * 二维码logo高度
	 */
	public static final int QR_LOGO_HEIGHT = Integer.valueOf(ConfigProperties.getSystemConfig("QR_LOGO_HEIGHT"));
	/**
	 * token 内存缓存
	 */
	public static ConcurrentMap<String, Long> TOKEN_CACHE = new ConcurrentHashMap<>();
}
