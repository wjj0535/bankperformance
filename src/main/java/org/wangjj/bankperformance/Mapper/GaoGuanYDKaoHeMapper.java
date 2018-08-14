package org.wangjj.bankperformance.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.wangjj.bankperformance.Entity.GaoGuanYDKaoHe;
import org.wangjj.bankperformance.Entity.JiGouYDKaoHe;
import org.wangjj.bankperformance.Entity.MenuInfo;

@Mapper
public interface GaoGuanYDKaoHeMapper {

	public void insert(GaoGuanYDKaoHe ggydkh);
	
	public List<GaoGuanYDKaoHe> selectByDutyAndYearMonth(GaoGuanYDKaoHe ggydkh);
	
	public void updateByUserIdAndYearMonth(GaoGuanYDKaoHe ggydkh);
}
