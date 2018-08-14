package org.wangjj.bankperformance.Service;

import java.util.List;

import org.wangjj.bankperformance.Entity.GaoGuanYDKaoHe;

public interface IGaoGuanYDKaoHeService {
	
	public boolean insert(GaoGuanYDKaoHe ggydkh);
	
	public List<GaoGuanYDKaoHe> getByDutyAndYearMonth(GaoGuanYDKaoHe ggydkh);
	
	public boolean modifyByInsIdAndYearMonth(GaoGuanYDKaoHe ggydkh);
}
