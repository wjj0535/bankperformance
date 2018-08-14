package org.wangjj.bankperformance.Mapper;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.wangjj.bankperformance.Entity.JiangJinChi;

public interface JiangJinChiMapper {

	public void insert(JiangJinChi jjc);
	
	public List<JiangJinChi> selectByYear(String year);
	
	public Set<String> selectAllYear();
	
	public JiangJinChi selectByDutyIdAndYear(Map<String,String> param);
	
	public void updateByDutyIdAndYear(JiangJinChi jjc);
	
	public void deleteByDutiIdAndYear(JiangJinChi jjc);
	
}
