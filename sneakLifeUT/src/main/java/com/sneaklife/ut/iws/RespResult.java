package com.sneaklife.ut.iws;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
		return "{" + "\"respCode\":" +
				respCode +
				",\"respMsg\":\"" +
				respMsg + '\"' +
				",\"respData\":" +
				respData +
				'}';
	}
}
