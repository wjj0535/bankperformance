package org.wangjj.bankperformance.Mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.wangjj.bankperformance.Entity.NianDuKaoHe;

@Mapper
public interface NianDuKaoHeMapper {

	public void insert(NianDuKaoHe ndkh);
	
	public NianDuKaoHe selectByUserIdAndYear(NianDuKaoHe ndkh);
	
	public List<NianDuKaoHe> selectByYearAndInsAndDuty(NianDuKaoHe ndkh);
	
	public List<NianDuKaoHe> selectByYearAndXulieId(NianDuKaoHe ndkh);
	
	public List<NianDuKaoHe> selectByYearAndDuty(NianDuKaoHe ndkh);
	
	public void updateByUserIdAndYear(NianDuKaoHe ndkh);
	
	public void updateScoreByUserIdAndYear(NianDuKaoHe ndkh);
	
	public void updateKhjgByUserIdAndYear(NianDuKaoHe ndkh);
}
