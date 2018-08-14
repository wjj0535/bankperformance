package org.wangjj.bankperformance.Mapper;

import java.util.List;

import org.springframework.stereotype.Service;
import org.wangjj.bankperformance.Entity.Dangliang;
import org.wangjj.bankperformance.Entity.Duty;

@Service
public interface DangliangMapper {
	
	public void insert(Dangliang dl);
	
	public List<Dangliang> selectAll();
	
	public Dangliang selectByCode(String code);
	
	public void updateByCode(Dangliang dl);
	
}
