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

public class UpdateGgYdKhJgWork implements Runnable{

	private String yearMonth;
	
	IYueDuKaoHeService yueDuKaoHeService = SpringContextUtil.getBean(IYueDuKaoHeService.class);
	
	IJiGouYDKaoHeService jiGouYDKaoHeService = SpringContextUtil.getBean(IJiGouYDKaoHeService.class);
	
	INianDuKaoHeService nianDuKaoHeService = SpringContextUtil.getBean(INianDuKaoHeService.class);
	
	IJiangJinChiService jiangJinChiService = SpringContextUtil.getBean(IJiangJinChiService.class);
	
	public UpdateGgYdKhJgWork(String yearMonth) {
		this.yearMonth = yearMonth;
	}

	public String getYearMonth() {
		return yearMonth;
	}

	public void setYearMonth(String yearMonth) {
		this.yearMonth = yearMonth;
	}
	
	/**
	 * 执行高管月度考核排序
	 */
	public void ExcuteSort()
	{
		YueDuKaoHe ydkh = new YueDuKaoHe();
		ydkh.setXulieId("1");
		ydkh.setYearMonth(yearMonth);
		List<YueDuKaoHe> ydkhList = yueDuKaoHeService.getByDateAndXulie(ydkh);
		boolean canSort = true;
		for(YueDuKaoHe item : ydkhList)
		{
			if(item.getScore() == 0)   //未计算分数
			{
				boolean goNext = false;
				float score = 0.0f;
				String[] szInsId = item.getChargeInsIds().split(",");
				//统计分管部门分数
				for(int i=0; i<szInsId.length; i++)
				{
					JiGouYDKaoHe jgydkh = new JiGouYDKaoHe();
					jgydkh.setInsId(szInsId[i]);
					jgydkh.setYearMonth(yearMonth);
					jgydkh.setIsYuanJiao("");
					JiGouYDKaoHe qJgydkh = jiGouYDKaoHeService.getByInsIdAndYearMonth(jgydkh);
					if(qJgydkh.getScore() == 0)
					{
						goNext = true;
						break;
					}
					score += qJgydkh.getScore();
				}
				//分管部门分数没打
				if(goNext)
				{
					canSort = false;
					continue;
				}
				//平均
				score = score/szInsId.length;
				//统计兼任部门分数
				float fpxs = 0.0f;
				if(item.getPtInsId()!=null && item.getPtInsId().length()>0)
				{
					JiGouYDKaoHe jgydkh = new JiGouYDKaoHe();
					jgydkh.setInsId(item.getPtInsId());
					jgydkh.setYearMonth(yearMonth);
					jgydkh.setIsYuanJiao("");
					JiGouYDKaoHe qJgydkh = jiGouYDKaoHeService.getByInsIdAndYearMonth(jgydkh);
					//兼任部门分数没打，继续计算下一个高管
					if(qJgydkh.getFpxs() == null || qJgydkh.getFpxs().equals(""))
					{
						canSort = false;
						continue;
					}
					fpxs = Float.valueOf(qJgydkh.getFpxs());
				}
				score = score*fpxs;
				//更新分数
				item.setScore(score);
				item.setYearMonth(yearMonth);
				yueDuKaoHeService.modifyScoreByUserIdAndDate(item);
			}
		}
		//计算排名
		if(canSort)
		{
			List<YueDuKaoHe> sortList = yueDuKaoHeService.getByDateAndXulie(ydkh);
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
					item.setYearMonth(yearMonth);
					yueDuKaoHeService.setKhjgByUserIdAndDate(item);
				}
				begin += size;
			}
		}
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		ExcuteSort();
	}

	
}
