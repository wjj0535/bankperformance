package org.wangjj.bankperformance.Service;

import java.util.List;

import org.wangjj.bankperformance.Entity.Dangliang;

public interface IDangliangService {
	
	public boolean insert(Dangliang dl);
	
	public List<Dangliang> getAll();
	
	public Dangliang getByCode(String code);
	
	public boolean modifyValueByCode(Dangliang dl);
}
