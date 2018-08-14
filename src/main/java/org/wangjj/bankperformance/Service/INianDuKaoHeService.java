package org.wangjj.bankperformance.Service;

import java.util.List;

import org.wangjj.bankperformance.Entity.NianDuKaoHe;
import org.wangjj.bankperformance.Entity.YueDuKaoHe;

public interface INianDuKaoHeService {
	
	public boolean insert(NianDuKaoHe ndkh);
	
	public NianDuKaoHe getByUserIdAndYear(NianDuKaoHe ndkh);
	
	public List<NianDuKaoHe> getCurInsDutyYearUsers(NianDuKaoHe ndkh);
	
	public List<NianDuKaoHe> getByYearAndXulieId(NianDuKaoHe ndkh);
	
	public List<NianDuKaoHe> getByYearAndDutyId(NianDuKaoHe ndkh);
	
	public boolean modifyByUserIdAndYear(NianDuKaoHe ndkh);
	
	public boolean modifyScoreByUserIdAndYear(NianDuKaoHe ndkh);
	
	public boolean setKhjgByUserIdAndDate(NianDuKaoHe ndkh);
}
