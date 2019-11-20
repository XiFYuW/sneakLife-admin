package com.sneaklife.ut.iws;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RespResult {

	/**
	 * 响应值
	 */
	private int respCode;
	/**
	 * 响应消息
	 */
	private String respMsg;
	/**
	 * 响应数据
	 */
	private Object respData;

	public RespResult(int respCode, String respMsg, Object respData) {
		this.respCode = respCode;
		this.respMsg = respMsg;
		this.respData = respData;
	}

	public RespResult() {
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
