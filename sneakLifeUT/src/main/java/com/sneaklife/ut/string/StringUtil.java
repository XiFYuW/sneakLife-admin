package com.sneaklife.ut.string;

public class StringUtil {

	public static String valueOf(Object o){
		String s = String.valueOf(o);
		return "null".equals(s) ? "" : s;
	}

	public static String disposeStrArray(String[] str, String patten) {
		String data = "";
		int len = str.length;
		for (int i = 0; i < len; i++) {
			String string = str[i];
			if (i == len - 1) {
				data += string;
			} else {
				data += string + patten;
			}
		}
		return data;
	}

	/**
	 * 过滤字符串处理
	 * @param value 待过滤字符串
	 * @return String
	 */
	public static String filterDangerString(String value) {
		if (value == null) {
			return null;
		}
		value = value.replaceAll("\\|", "");
		value = value.replaceAll("&", "&");
		value = value.replaceAll(";", "");
		value = value.replaceAll("'", "");
		value = value.replaceAll("<", "<");
		value = value.replaceAll(">", ">");
		value = value.replaceAll("\\(", "");
		value = value.replaceAll("\\)", "");
		value = value.replaceAll("\\+", "");
		value = value.replaceAll("\r", "");
		value = value.replaceAll("\n", "");
		value = value.replaceAll("script", "");
		value = value.replaceAll(">", "");
		value = value.replaceAll("<", "");
		value = value.replaceAll("=", "");
		value = value.replaceAll("/", "");
		return value;
	}

	/**
	 * 过滤多个字符串处理
	 *
	 * @param value 待过滤字符串
	 * @return String[]
	 */
	public static String[] filterDangerString(String[] value) {
		if (value == null) {
			return null;
		}
		for (int i = 0; i < value.length; i++) {
			String val = filterDangerString(value[i]);
			value[i] = val;
		}
		return value;
	}

	/**
	 * 此方法适用String.valueOf()之后使用
	 * @param s 字符串
	 * @return boolean
	 */
	public static boolean isEmpty(String s){
		return "".equals(s) || "null".equals(s);
	}
}
