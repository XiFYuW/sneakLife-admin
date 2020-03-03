package com.sneaklife.ut.iws;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
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
	/**
	 * 字段输入检测id
	 */
	private String checkInId;

	@Override
	public String toString() {
		return "{" + "\"me\":\"" +
				me + '\"' +
				",\"data\":\"" +
				data + '\"' +
				",\"pag\":\"" +
				pag + '\"' +
				",\"checkInId\":\"" +
				checkInId + '\"' +
				'}';
	}
}
