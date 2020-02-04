package com.sneaklife.ut.iws;

import lombok.Getter;

@Getter
public class RespResult {

	/**
	 * 响应值
	 */
	private final int respCode;
	/**
	 * 响应消息
	 */
	private final String respMsg;
	/**
	 * 响应数据
	 */
	private final Object respData;

	public RespResult(int respCode, String respMsg, Object respData) {
		this.respCode = respCode;
		this.respMsg = respMsg;
		this.respData = respData;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("{");
		sb.append("\"respCode\":")
				.append(respCode);
		sb.append(",\"respMsg\":\"")
				.append(respMsg).append('\"');
		sb.append(",\"respData\":")
				.append(respData);
		sb.append('}');
		return sb.toString();
	}
}
