package org.wangjj.bankperformance.Enum;

public enum LOGIN_STATE {
	LOGIN("1"), 
	LOGOUT("0");

    private final String str;
    //构造方法必须是private或者默认
    private LOGIN_STATE(String str) {
        this.str = str;
    }
	public String getStr() {
		return str;
	}
}
