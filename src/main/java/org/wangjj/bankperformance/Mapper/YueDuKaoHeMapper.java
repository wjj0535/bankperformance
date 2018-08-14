package org.wangjj.bankperformance.Mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.wangjj.bankperformance.Entity.TotalZL;
import org.wangjj.bankperformance.Entity.YueDuKaoHe;

@Mapper
public interface YueDuKaoHeMapper {

	public void insert(YueDuKaoHe ydkh);
	
	public YueDuKaoHe selectByUserIdAndDate(YueDuKaoHe ydkh);
	
	public List<YueDuKaoHe> selectByDateAndInsAndDuty(YueDuKaoHe ydkh);
	
	public List<YueDuKaoHe> selectByDateAndXulie(YueDuKaoHe ydkh);
	
	public List<YueDuKaoHe> selectByDutyIdAndYearMonth(YueDuKaoHe ydkh);
	
	public List<YueDuKaoHe> selectByUserIdAndYear(Map<String, String> param);
	
	public List<TotalZL> selectTotalByDutyIdAndYear(Map<String, String> param);
	
	public TotalZL selectTotalByUserIdAndYear(Map<String, String> param);
	
	public void updateByUserIdAndDate(YueDuKaoHe ydkh);
	
	public void updateScoreByUserIdAndDate(YueDuKaoHe ydkh);
	
	public void updateKhjgByUserIdAndDate(YueDuKaoHe ydkh);
}
