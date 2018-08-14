package org.wangjj.bankperformance.Service;

import java.util.List;
import java.util.Set;

import org.wangjj.bankperformance.Entity.JiangJinChi;

public interface IJiangJinChiService {
	
	public boolean insert(JiangJinChi jjc);
	
	public List<JiangJinChi> getByYear(String year);
	
	public Set<String> getAllYears();
	
	public JiangJinChi getByDutyIdAndYear(String dutyId, String year);
	
	public boolean modifyByYearAndDutyId(JiangJinChi jjc);
	
	public boolean removeByYearAndDutyId(JiangJinChi jjc);
	
	
}
