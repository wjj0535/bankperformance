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

public class CalYgNdKhJgWork implements Runnable{

	private String userId;
	private String dutyId;
	private String year;
	
	IYueDuKaoHeService yueDuKaoHeService = SpringContextUtil.getBean(IYueDuKaoHeService.class);
	
	IJiGouNDKaoHeService jiGouNDKaoHeService = SpringContextUtil.getBean(IJiGouNDKaoHeService.class);
	
	INianDuKaoHeService nianDuKaoHeService = SpringContextUtil.getBean(INianDuKaoHeService.class);
	
	IJiangJinChiService jiangJinChiService = SpringContextUtil.getBean(IJiangJinChiService.class);
	
	public CalYgNdKhJgWork(String userId, String dutyId, String year) {
		this.userId = userId;
		this.dutyId = dutyId;
		this.year = year;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	/**
	 * 计算分数并更新
	 */
	public void ExcuteCalKhjg()
	{
		//计算年度考核分数
		List<YueDuKaoHe> someoneYdkhList = yueDuKaoHeService.getByUserIdAndYear(userId, year);
		float totalScore = 0.0f;
		for(YueDuKaoHe item : someoneYdkhList)
		{
			totalScore += item.getScore();
		}
		NianDuKaoHe qndkh = new NianDuKaoHe();
		qndkh.setUserId(userId);
		qndkh.setYear(year);
		NianDuKaoHe ndkh = nianDuKaoHeService.getByUserIdAndYear(qndkh);
		if(ndkh != null)
		{
			ndkh.setScore(String.valueOf(totalScore));
			nianDuKaoHeService.modifyScoreByUserIdAndYear(ndkh);
		}
		//查询同序列其他人员分数
		String xulieId = CommonUtil.getXulieIdByDutyId(dutyId);
		ndkh.setXulieId(xulieId);
		List<NianDuKaoHe> ndkhList = nianDuKaoHeService.getByYearAndXulieId(ndkh);
		boolean canSort = true;
		for(NianDuKaoHe item : ndkhList)
		{
			//有没打完分的 不做排序
			if(null == item.getScore() || "".equals(item.getScore()))
			{
				canSort = false;
				break;
			}
		}
		if(canSort)
		{
			List<Integer> sortNum = CalUtil.getPMCount(ndkhList.size());
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
					NianDuKaoHe item = ndkhList.get(j+begin);
					item.setRank(++rank);
					item.setLevel(level);
					nianDuKaoHeService.setKhjgByUserIdAndDate(item);
				}
				begin += size;
			}
		}
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		ExcuteCalKhjg();
	}

	
}
