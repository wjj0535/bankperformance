package org.wangjj.bankperformance.Constant;

import org.springframework.context.ConfigurableApplicationContext;

public class GlobalData {
	
	private static String mailSmtpHost;
	private static String mailSmtpPort;
	private static String mailUserName;
	private static String mailUserPwd;
	
	
	private static String esIp;
	private static String esPort;
	private static int esBulkSize = 5;
	private static int esBulkSecond = 3;
	private static int esBulkThread = 5;
	
	private static String serverName;
	
	public static String getMailSmtpHost() {
		return mailSmtpHost;
	}
	public static void setMailSmtpHost(String mailSmtpHost) {
		GlobalData.mailSmtpHost = mailSmtpHost;
	}
	public static String getMailSmtpPort() {
		return mailSmtpPort;
	}
	public static void setMailSmtpPort(String mailSmtpPort) {
		GlobalData.mailSmtpPort = mailSmtpPort;
	}
	public static String getMailUserName() {
		return mailUserName;
	}
	public static void setMailUserName(String mailUserName) {
		GlobalData.mailUserName = mailUserName;
	}
	public static String getMailUserPwd() {
		return mailUserPwd;
	}
	public static void setMailUserPwd(String mailUserPwd) {
		GlobalData.mailUserPwd = mailUserPwd;
	}
	public static String getServerName() {
		return serverName;
	}
	public static void setServerName(String serverName) {
		GlobalData.serverName = serverName;
	}
		
	public static String getEsIp() {
		return esIp;
	}
	public static void setEsIp(String esIp) {
		GlobalData.esIp = esIp;
	}
	public static String getEsPort() {
		return esPort;
	}
	public static void setEsPort(String esPort) {
		GlobalData.esPort = esPort;
	}
	public static int getEsBulkSize() {
		return esBulkSize;
	}
	public static void setEsBulkSize(int esBulkSize) {
		GlobalData.esBulkSize = esBulkSize;
	}
	public static int getEsBulkSecond() {
		return esBulkSecond;
	}
	public static void setEsBulkSecond(int esBulkSecond) {
		GlobalData.esBulkSecond = esBulkSecond;
	}
	public static int getEsBulkThread() {
		return esBulkThread;
	}
	public static void setEsBulkThread(int esBulkThread) {
		GlobalData.esBulkThread = esBulkThread;
	}
	public static boolean initGlobalData(ConfigurableApplicationContext context)
	{
		String serverName = context.getEnvironment().getProperty("server.name");
		if(null == serverName || "".equals(serverName))
		{
			return false;
		}
		GlobalData.setServerName(context.getEnvironment().getProperty("server.name"));
		GlobalData.setMailSmtpHost(context.getEnvironment().getProperty("user.subscribe.sendmail.addr"));
		GlobalData.setMailSmtpPort(context.getEnvironment().getProperty("user.subscribe.sendmail.port"));
		GlobalData.setMailUserName(context.getEnvironment().getProperty("user.subscribe.sendmail.username"));
		GlobalData.setMailUserPwd(context.getEnvironment().getProperty("user.subscribe.sendmail.pwd"));
		
		GlobalData.setEsBulkSize(Integer.valueOf(context.getEnvironment().getProperty("es.bulk.persize")));
		GlobalData.setEsBulkThread(Integer.valueOf(context.getEnvironment().getProperty("es.bulk.thread")));
		
		return true;
	}
}
