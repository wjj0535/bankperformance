package org.wangjj.bankperformance.Utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.wangjj.bankperformance.Enum.YG_LEVEL;

public class CalUtil {
	
	/**
	 * 按等级比例计算每组个数
	 * @param totalCount
	 * @return
	 */
	public static List<Integer> getPMCount(int totalCount)
	{
		int exc = 0;
		List<Integer> retList = new ArrayList<>();
		//A+
		double fAPlusCount = totalCount*YG_LEVEL.A_PLUS.getPercent();
		BigDecimal ap = new BigDecimal(new Double(fAPlusCount).toString());
		int nAP = ap.setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
		//int nAP = (int) (Math.round(fAPlusCount));
		if(nAP <= 0)
			nAP += 1;
		retList.add(nAP);
		exc += nAP;
		if(exc >= totalCount)
			return retList;
		//A+
		double fACount = totalCount*YG_LEVEL.A.getPercent();
		BigDecimal a = new BigDecimal(new Double(fACount).toString());
		int nA = a.setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
		if(nA <= 0)
			nA += 1;
		retList.add(nA);
		exc += nA;
		if(exc >= totalCount)
			return retList;
		//B+
		double fBPlusCount = totalCount*YG_LEVEL.B_PLUS.getPercent();
		BigDecimal bp = new BigDecimal(new Double(fBPlusCount).toString());
		int nBP = bp.setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
		if(nBP <= 0)
			nBP += 1;
		retList.add(nBP);
		exc += nBP;
		if(exc >= totalCount)
			return retList;
		//C
		double fCCount = totalCount*YG_LEVEL.C.getPercent();
		BigDecimal b = new BigDecimal(new Double(fCCount).toString());
		int nC = b.setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
		//B
		exc += nC;
		int nB = totalCount - exc;
		retList.add(nB);
		
		retList.add(nC);
				
		return retList;
	}
	public static List<String> getJiangJinList(String totalMoney, String step, int size)
	{
		List<String> retList = new ArrayList<>();
		double dTotal = Double.valueOf(totalMoney);
		double dStep = Double.valueOf(step);
		double a1 = (dTotal - size*(size-1)*dStep/2)/dStep;
		for(int i=size; i>0; i--)
		{
			double an = a1+(i-1)*dStep;
			BigDecimal b = new BigDecimal(an);
			String jj = b.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
			retList.add(jj);
		}
		return retList;
	}
}
