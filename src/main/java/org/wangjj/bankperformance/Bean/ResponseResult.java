package org.wangjj.bankperformance.Bean;

/**
 * 
 * 说明：日志处理结果
 * 时间：2018年3月26日
 * 作者：wangjunjie
 */
public class ResponseResult {
	//错误码
	private String code;
	//错误消息
	private String msg;
	
	private QueryResult<?> responseData;
	
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
	public QueryResult<?> getResponseData() {
		return responseData;
	}
	public void setResponseData(QueryResult<?> responseData) {
		this.responseData = responseData;
	}
	
}
