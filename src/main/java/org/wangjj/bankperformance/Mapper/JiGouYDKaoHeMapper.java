package org.wangjj.bankperformance.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.wangjj.bankperformance.Entity.JiGouYDKaoHe;
import org.wangjj.bankperformance.Entity.MenuInfo;

@Mapper
public interface JiGouYDKaoHeMapper {

	public void insert(JiGouYDKaoHe jgydkh);
	
	public JiGouYDKaoHe selectByInsIdAndYearMonth(JiGouYDKaoHe jgydkh);
	
	public List<JiGouYDKaoHe> selectByTypeAndYearMonth(JiGouYDKaoHe jgydkh);
	
	public List<JiGouYDKaoHe> selectByInsIdAndYear(JiGouYDKaoHe jgydkh);
	
	public void updateByInsIdAndYearMonth(JiGouYDKaoHe jgydkh);
	
	public void updateKhjgByInsIdAndYearMonth(JiGouYDKaoHe jgydkh);
}
