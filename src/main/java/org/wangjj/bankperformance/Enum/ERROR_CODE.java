package org.wangjj.bankperformance.Enum;

public enum ERROR_CODE {
	
	UNKOWNERROR("9999", "未知错误"),
	SUCCESS("0000", "处理成功"),	
	FAIL("0001", "处理失败"),	
	NOT_LOGIN("0003", "未登录"),
	PWD_ERROR("0004", "密码错误"),
	QUERY_DATA_ERROR("0005", "起始日期大于截止日期"),
	QUERY_USERINFO_ERROR("0006", "查询用户状态失败"),
	QUERY_YGYDKHJG_ERROR("0007", "有员工未录入月度存款数"),
	MODIFY_PWD_ERROR("0008", "修改密码失败"),
	NOT_SET_JJC("0009", "未设置该序列奖金池，请联系总部管理员"),
	NOT_JG_FPXS("0010", "未对该机构打分"),
	NOT_SET_NDJS("0011", "未设置本年度考核基数");
	
	private String code;
	private String msg;

	// 构造方法必须是private或者默认
	private ERROR_CODE(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	
}
