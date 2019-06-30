package com.sneaklife.resp;

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
		super();
		this.respCode = respCode;
		this.respMsg = respMsg;
		this.respData = respData;
	}

	public RespResult(int respCode, Object respData) {
		super();
		this.respCode = respCode;
		this.respData = respData;
	}

	public RespResult(int respCode, String respMsg) {
		super();
		this.respCode = respCode;
		this.respMsg = respMsg;
	}

	public RespResult() {
		super();
	}

	public int getRespCode() {
		return respCode;
	}

	public void setRespCode(int respCode) {
		this.respCode = respCode;
	}

	public String getRespMsg() {
		return respMsg;
	}

	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}

	public Object getRespData() {
		return respData;
	}

	public void setRespData(Object respData) {
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
