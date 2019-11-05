package com.sneaklife.util.code.req;

public class ReqParam {

	/**
	 * 请求方法
	 */
	private String me;
	/**
	 * 请求参数
	 */
	private String data;
	/**
	 * 分页参数
	 */
	private String pag;

	public String getMe() {
		return me;
	}

	public void setMe(String me) {
		this.me = me;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getPag() {
		return pag;
	}

	public void setPag(String pag) {
		this.pag = pag;
	}
}
