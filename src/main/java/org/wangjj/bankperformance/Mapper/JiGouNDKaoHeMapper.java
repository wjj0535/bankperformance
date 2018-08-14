package org.wangjj.bankperformance.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.wangjj.bankperformance.Entity.JiGouNDKaoHe;

@Mapper
public interface JiGouNDKaoHeMapper {

	public void insert(JiGouNDKaoHe jgndkh);
	
	public JiGouNDKaoHe selectByInsIdAndYear(JiGouNDKaoHe jgndkh);
	
	public List<JiGouNDKaoHe> selectByTypeAndYear(JiGouNDKaoHe jgndkh);
	
	public void updateByInsIdAndYear(JiGouNDKaoHe jgndkh);
	
	public void updateKhjgByInsIdAndYear(JiGouNDKaoHe jgndkh);
	
	public void deleteByInsIdAndYear(JiGouNDKaoHe jgndkh);
	
	public List<String> selectGroupByInsIdAndYear(@Param(value="insId") String insId);
}
