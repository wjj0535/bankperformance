package org.wangjj.bankperformance.Service;

import java.util.List;

import org.wangjj.bankperformance.Entity.Duty;

public interface IDutyService {
	public List<Duty> getAllDuty();
	
	public Duty getById(String id);
}
