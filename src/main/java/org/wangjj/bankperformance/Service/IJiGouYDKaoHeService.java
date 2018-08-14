package org.wangjj.bankperformance.Service;

import java.util.List;

import org.wangjj.bankperformance.Entity.JiGouYDKaoHe;

public interface IJiGouYDKaoHeService {
	
	public boolean insert(JiGouYDKaoHe jgydkh);
	
	public JiGouYDKaoHe getByInsIdAndYearMonth(JiGouYDKaoHe jgydkh);
	
	public List<JiGouYDKaoHe> getByTypeAndYearMonth(JiGouYDKaoHe jgydkh);
	
	public List<JiGouYDKaoHe> getByInsIdAndYear(JiGouYDKaoHe jgydkh);
	
	public boolean modifyByInsIdAndYearMonth(JiGouYDKaoHe jgydkh);
	
	public boolean setKhjgByInsIdAndYearMonth(JiGouYDKaoHe jgydkh);
}
