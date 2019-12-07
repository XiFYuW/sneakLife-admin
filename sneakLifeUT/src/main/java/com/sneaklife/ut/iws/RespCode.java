package com.sneaklife.ut.iws;

public enum RespCode {
	/**
	 * 参数不为空！
	 */
	MSG_NOT_RULE(2589, "没有该规则配置"),
	/**
	 * 参数不为空！
	 */
	MSG_IS_NOT_NULL(3879, "--参数不为空！"),
	/**
	 * 获取秘钥对失败
	 */
	MSG_HQMYDSB(3258, "获取秘钥对失败"),
	/**
	 * 数字签名校验不通过,请重试！
	 */
	MSG_SZQMJYBTG(3000, "数字签名校验不通过,请重试！"),
	/**
	 * AES解密参数失败,请重试！
	 */
	MSG_AES_JMSB(5559, "AES解密参数失败,请重试！"),
	/**
	 * RSA解密参数失败,请重试！
	 */
	MSG_RSA_JMSB(5550, "RSA解密参数失败,请重试！"),
	/**
	 * 系统异常,请联系管理员！
	 */
	MSG_XTYC(5000, "系统异常,请联系管理员！"),
	/**
	 * 操作成功
	 */
	MSG_SUCCEED(2000, "操作成功"),
	/**
	 * 添加成功
	 */
	MSG_TJCG(5566, "添加成功！"),
	/**
	 * 添加失败
	 */
	MSG_TJSB(5567, "添加失败！"),
	/**
	 * 删除失败
	 */
	MSG_SCSB(4467, "删除失败！"),
	/**
	 * 删除成功
	 */
	MSG_SCCG(4466, "删除成功！"),
	/**
	 * 修改失败
	 */
	MSG_XGSB(7767, "修改失败！"),
	/**
	 * 修改成功
	 */
	MSG_XGCG(7766, "修改成功！"),
	/**
	 * Token过期，重新获取
	 */
	MSG_GQTOKEN(2038, "Token过期，重新获取"),
	/**
	 * 分页参数有误，请检查
	 */
	MSG_PAGE_ERR(1055, "分页参数有误，请检查"),
	/**
	 * 参数非法，不存在根节点
	 */
	MSG_PARAM_ILLEGAL_NOT_ROOT(1055, "参数非法，不存在根节点");

	private final int value;
	private final String msg;

	RespCode(int value, String msg) {
		this.value = value;
		this.msg = msg;
	}

	public int toValue() {
		return this.value;
	}

	public String toMsg() {
		return this.msg;
	}
}
