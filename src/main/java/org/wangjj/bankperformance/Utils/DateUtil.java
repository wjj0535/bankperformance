package org.wangjj.bankperformance.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.wangjj.bankperformance.Constant.ConstantData;

public class DateUtil {
	
	private static Logger logger = LoggerFactory.getLogger(DateUtil.class); 
	
	public static String getCurTimeWithStandFormat()
	{
		SimpleDateFormat sdf = new SimpleDateFormat(ConstantData.THISSYS_STANDARD_DATETIMEFORMATE);
		//sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		return sdf.format(new Date());
	}
	public static String getDateBySpitStr(String strDate)
	{
		SimpleDateFormat sdf = new SimpleDateFormat(ConstantData.THISSYS_STANDARD_DATETIMEFORMATE);
		Date date = null;
		try {
			date = sdf.parse(strDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("日期截取失败，输入格式错误！", e);
			return "";
		}
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd");
		return sdf2.format(date);
	}
	/**
	 * 
	 * 功能：
	 * 时间：2018年4月12日
	 * 作者：wangjunjie
	 * 返回值：0 相等        >0 第一个大    <0 第二个大 
	 */
	public static int CompareDataTime(String dt1, String dt2)
	{
		return dt1.compareTo(dt2);
	}
	public static void main(String[] args) {
		System.out.println(DateUtil.getCurTimeWithStandFormat());
		System.out.println(DateUtil.getDateBySpitStr(DateUtil.getCurTimeWithStandFormat()));
	
	}
}
