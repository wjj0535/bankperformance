package org.wangjj.bankperformance.Service;

import java.util.List;

import org.wangjj.bankperformance.Entity.JiGouNDKaoHe;

public interface IJiGouNDKaoHeService {
	
	public boolean insert(JiGouNDKaoHe jgndkh);
	
	public JiGouNDKaoHe getByInsIdAndYear(JiGouNDKaoHe jgndkh);
	
	public List<JiGouNDKaoHe> getByTypeAndYear(JiGouNDKaoHe jgndkh);
	
	public boolean modifyByInsIdAndYear(JiGouNDKaoHe jgndkh);
	
	public boolean setKhjgByInsIdAndYear(JiGouNDKaoHe jgndkh);
	
	public boolean deleteByInsIdAndYear(JiGouNDKaoHe jgndkh);
	
	public List<String> getYearListByInsId(String insId);
}
