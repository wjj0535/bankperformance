package org.wangjj.bankperformance.Mapper;

import java.util.List;

import org.springframework.stereotype.Service;
import org.wangjj.bankperformance.Entity.Duty;

@Service
public interface DutyMapper {
	
	public List<Duty> selectAllDuty();
	
	public Duty selectById(String id);
}
