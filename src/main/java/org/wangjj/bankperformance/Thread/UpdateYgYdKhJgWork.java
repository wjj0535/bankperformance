package org.wangjj.bankperformance.Thread;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.wangjj.bankperformance.Entity.JiGouYDKaoHe;
import org.wangjj.bankperformance.Entity.JiangJinChi;
import org.wangjj.bankperformance.Entity.NianDuKaoHe;
import org.wangjj.bankperformance.Entity.TotalZL;
import org.wangjj.bankperformance.Entity.YueDuKaoHe;
import org.wangjj.bankperformance.Enum.YG_LEVEL;
import org.wangjj.bankperformance.Service.IJiGouNDKaoHeService;
import org.wangjj.bankperformance.Service.IJiGouYDKaoHeService;
import org.wangjj.bankperformance.Service.IJiangJinChiService;
import org.wangjj.bankperformance.Service.INianDuKaoHeService;
import org.wangjj.bankperformance.Service.IYueDuKaoHeService;
import org.wangjj.bankperformance.Utils.CalUtil;
import org.wangjj.bankperformance.Utils.CommonUtil;
import org.wangjj.bankperformance.Utils.SpringContextUtil;

public class UpdateYgYdKhJgWork implements Runnable{

	private String dutyId;
	private String yearMonth;
	
	IYueDuKaoHeService yueDuKaoHeService = SpringContextUtil.getBean(IYueDuKaoHeService.class);
	
	IJiGouYDKaoHeService jiGouYDKaoHeService = SpringContextUtil.getBean(IJiGouYDKaoHeService.class);
	
	INianDuKaoHeService nianDuKaoHeService = SpringContextUtil.getBean(INianDuKaoHeService.class);
	
	IJiangJinChiService jiangJinChiService = SpringContextUtil.getBean(IJiangJinChiService.class);
	
	public UpdateYgYdKhJgWork(String dutyId, String yearMonth) {
		this.dutyId = dutyId;
		this.yearMonth = yearMonth;
	}

	public String getDutyId() {
		return dutyId;
	}

	public void setDutyId(String dutyId) {
		this.dutyId = dutyId;
	}

	public String getYearMonth() {
		return yearMonth;
	}

	public void setYearMonth(String yearMonth) {
		this.yearMonth = yearMonth;
	}
	
	/**
	 * 执行月度考核排序
	 */
	public void ExcuteSort()
	{
		String xulieId = CommonUtil.getXulieIdByDutyId(dutyId);
		YueDuKaoHe ydkh = new YueDuKaoHe();
		ydkh.setXulieId(xulieId);
		ydkh.setYearMonth(yearMonth);
		List<YueDuKaoHe> sortList = yueDuKaoHeService.getByDateAndXulie(ydkh);
		String year = yearMonth.substring(0, 4);
		JiangJinChi jjc = jiangJinChiService.getByDutyIdAndYear(dutyId, year);
		List<String> jjList = CalUtil.getJiangJinList(jjc.getTotalMoney(), jjc.getStep(), sortList.size());					
		List<Integer> sortNum = CalUtil.getPMCount(sortList.size());
		int begin = 0;
		int rank = 0;
		for(int i=0; i<sortNum.size(); i++)
		{
			int size = sortNum.get(i).intValue();
			String level = "";
			switch(i)
			{
			case 0:
				level = YG_LEVEL.A_PLUS.getCode();
				break;
			case 1:
				level = YG_LEVEL.A.getCode();
				break;
			case 2:
				level = YG_LEVEL.B_PLUS.getCode();
				break;
			case 3:
				level = YG_LEVEL.B.getCode();
				break;
			case 4:
				level = YG_LEVEL.C.getCode();
				break;
			}
			for(int j=0; j<sortNum.get(i); j++)
			{
				YueDuKaoHe item = sortList.get(j+begin);
				item.setRank(++rank);
				item.setLevel(level);
				Double djj = Double.valueOf(jjList.get(rank-1));
				//如果没有机构分配系数 则下一个
				if(item.getJgFpxs() == null || "".equals(item.getJgFpxs()))
					continue;
				Double fpxs = Double.valueOf(item.getJgFpxs());
				BigDecimal b = new BigDecimal(fpxs*djj);
				item.setJiangJin(b.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				item.setYearMonth(yearMonth);
				yueDuKaoHeService.setKhjgByUserIdAndDate(item);
			}
			begin += size;
		}
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		ExcuteSort();
	}

	
}
