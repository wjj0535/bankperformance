package org.wangjj.bankperformance.Service;

import java.util.List;

import org.wangjj.bankperformance.Entity.Institution;

public interface IInstitutionService {
	public List<Institution> getAllInstitution();
	
	public Institution getInstitutionByInsId(String insId);
}
