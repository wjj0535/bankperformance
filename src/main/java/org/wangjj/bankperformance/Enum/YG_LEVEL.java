package org.wangjj.bankperformance.Enum;

public enum YG_LEVEL {
	
	A_PLUS("A+", 1.2f, 0.1f),
	A("A", 1.1f, 0.15f),	
	B_PLUS("B+", 1.0f, 0.2f),	
	B("B", 0.9f, 0.5f),
	C("C", 0.8f, 0.05f);
	
	private String code;
	private float fpxs;
	private float percent;

	// 构造方法必须是private或者默认
	private YG_LEVEL(String code, float fpxs, float percent) {
		this.code = code;
		this.fpxs = fpxs;
		this.percent = percent;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public float getFpxs() {
		return fpxs;
	}
	public void setFpxs(float fpxs) {
		this.fpxs = fpxs;
	}

	public float getPercent() {
		return percent;
	}

	public void setPercent(float percent) {
		this.percent = percent;
	}
	
}
