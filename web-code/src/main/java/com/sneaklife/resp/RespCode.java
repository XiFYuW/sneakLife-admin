package com.sneaklife.resp;

public enum RespCode {
	/**
	 * 文件上传失败
	 */
	MSG_FILEUPLOAD_NO(4222, "文件上传失败"),
	/**
	 * 文件上传成功
	 */
	MSG_FILEUPLOAD_OK(4222, "文件上传成功"),
	/**
	 * 数字签名校验不通过,请重试！
	 */
	MSG_SZQMJYBTG(3000, "数字签名校验不通过,请重试！"),
	/**
	 * 解密参数失败,请重试！
	 */
	MSG_JMSBZS(5559, "解密参数失败,请重试！"),
	/**
	 * 系统异常,请联系管理员！
	 */
	MSG_XTYC(5000, "系统异常,请联系管理员！"),
	/**
	 * 没有权限删除该记录！
	 */
	MSG_NOESCQX(9079, "没有权限删除该记录！"),
	/**
	 * 登录失败：账号或密码错误
	 */
	MSG_LOGIN_DEFEATED(1010, "登录失败：账号或密码错误"),
	/**
	 * 密码不一致
	 */
	MSG_MMBYZ(4456, "密码不一致"),
	/**
	 * 图片错误
	 */
	MSG_TPCW(2342, "图片错误"),
	/**
	 * 信息不完善,请完善信息
	 */
	MSG_XXBWS(4040, "信息不完善,请完善信息"),
	/**
	 * 该账户已登录,请重新登录!!!
	 */
	MSG_USERZX(9542, "该账户已在异地登录,请重新登录!!!"),
	/**
	 * 操作成功
	 */
	MSG_SUCCEED(2000, "操作成功"),
	/**
	 * 不支持的HTTP方法
	 */
	MSG_NOTHTTP(4004, "不支持的HTTP方法"),
	/**
	 * 登录成功
	 */
	MSG_LOGIN_SUCCEED(1000, "登录成功"),
	/**
	 * 操作失败
	 */
	MSG_DEFEATED(2002, "操作失败"),
	/**
	 * 网络请求超时，请重试
	 */
	MSG_OVERTIME(6003, "网络请求超时，请重试"),
	/**
	 * 初始化token
	 */
	MSG_CSTOKEN(2018, "初始化token"),
	/**
	 * 参数异常，请检查参数
	 */
	MSG_CSYC(3308, "参数异常，请检查参数"),
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
	 * 图片验证码正确
	 */
	MSG_VERIFYZQ(1006, "图片验证码正确"),
	/**
	 * 图片验证码不能为空
	 */
	MSG_VERIFYZQBNWK(1009, "图片验证码不能为空"),
	/**
	 * 验证码错误
	 */
	MSG_VERIFYCW(1004, "验证码错误"),
	/**
	 * 手机号不能为空
	 */
	MSG_SJHBNWK(1333, "手机号不能为空"),
	/**
	 * 手机验证码不能为空
	 */
	MSG_SJYZMBNWK(1334, "手机验证码不能为空"),
	/**
	 * 验证码过期
	 */
	MSG_YZMGQ(7090, "验证码过期"),
	/**
	 * 验证码失效
	 */
	MSG_YZMSX(1006, "验证码失效"),
	/**
	 * 手机验证码错误
	 */
	MSG_SJYZMCW(1678, "手机验证码错误"),
	/**
	 * 手机验证码发送次数过多
	 */
	MSG_SJYZMSGD(2007, "手机验证码发送次数过多"),
	/**
	 * 手机验证码为：
	 */
	MSG_SJYZMW(1667, "手机验证码为："),
	/**
	 * 邮箱发送成功！
	 */
	MSG_SENDMAILCG(8669, "邮箱发送成功！"),
	/**
	 * 发送邮箱失败！
	 */
	MSG_SENDMAILSB(8654, "发送邮箱失败！"),
	/**
	 * 邮箱验证成功！
	 */
	MSG_YZMAILCG(5879, "邮箱验证成功！"),
	/**
	 * 邮箱地址错误，请检查！
	 */
	MSG_YXDZCW(5857, "邮箱地址错误，请检查！"),
	/**
	 * 邮箱验证码错误！
	 */
	MSG_YZMAILSB(5834, "邮箱验证码错误！"),
	/**
	 * 邮箱验证码过期！
	 */
	MSG_YZMAILGQ(5838, "邮箱验证码过期！"),
	/**
	 * 邮箱验证码不能为空！
	 */
	MSG_YZMAILBNWK(5839, "邮箱验证码不能为空！"),
	/**
	 * 非法用户，请登录
	 */
	MSG_FFUSERS(5555, "非法用户，请登录"),
	/**
	 * 必填参数存在空值！
	 */
	MSG_BTCSCZKZ(9966, "必填参数存在空值！"),
	/**
	 * Token过期，重新获取
	 */
	MSG_GQTOKEN(2038, "Token过期，重新获取"),
	/**
	 * 分页参数有误，请检查
	 */
	MSG_PAGE_ERR(1055, "分页参数有误，请检查");

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
