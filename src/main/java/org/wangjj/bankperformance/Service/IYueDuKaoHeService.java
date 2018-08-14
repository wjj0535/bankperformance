package org.wangjj.bankperformance.Service;

import java.util.List;
import java.util.Map;

import org.wangjj.bankperformance.Entity.TotalZL;
import org.wangjj.bankperformance.Entity.YueDuKaoHe;

public interface IYueDuKaoHeService {
	
	public boolean insert(YueDuKaoHe ydkh);
	
	public YueDuKaoHe getByUserIdAndDate(YueDuKaoHe ydkh);
	
	public List<YueDuKaoHe> getCurInsDutyDateUsers(YueDuKaoHe ydkh);
	
	public List<YueDuKaoHe> getByDateAndXulie(YueDuKaoHe ydkh);
	
	public List<YueDuKaoHe> getByDutyIdAndYearMonth(YueDuKaoHe ydkh);
	
	public List<YueDuKaoHe> getByUserIdAndYear(String userId, String year);
	
	public List<TotalZL> 	getTotalByDutyIdAndYear(String dutyId, String year);
	
	public TotalZL		 	getTotalByUserIdAndYear(String userId, String year);
	
	public boolean 			modifyByUserIdAndDate(YueDuKaoHe ydkh);
	
	public boolean 			modifyScoreByUserIdAndDate(YueDuKaoHe ydkh);
	
	public boolean 			setKhjgByUserIdAndDate(YueDuKaoHe ydkh);
}
