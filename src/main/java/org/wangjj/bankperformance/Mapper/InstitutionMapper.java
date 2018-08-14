package org.wangjj.bankperformance.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.wangjj.bankperformance.Entity.Institution;

@Service
public interface InstitutionMapper {
	
	public List<Institution> selectAll();
	
	public Institution selectByInsId(@Param(value="insId")String insId);
 }
