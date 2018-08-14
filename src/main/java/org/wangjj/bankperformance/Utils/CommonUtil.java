package org.wangjj.bankperformance.Utils;

public class CommonUtil {
	
	public static String getXulieIdByDutyId(String dutyId)
	{
		int nDutyId = Integer.valueOf(dutyId);
		if(nDutyId <= 8)
			return "1";
		if(nDutyId>=9 && nDutyId<=12)
			return "2";
		else
			return dutyId;
	}
	
	public static String getLayoutDataByInsId(String insId)
	{
		String ret = null;
		switch(insId)
		{
		case "1":
			ret = "DongShiHuiBanGongShi.json";
			break;
		}
		return ret;
	}
}
