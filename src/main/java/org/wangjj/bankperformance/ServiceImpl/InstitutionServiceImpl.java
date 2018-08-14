package org.wangjj.bankperformance.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wangjj.bankperformance.Entity.Duty;
import org.wangjj.bankperformance.Entity.Institution;
import org.wangjj.bankperformance.Mapper.ChaxunMenuMapper;
import org.wangjj.bankperformance.Mapper.DutyMapper;
import org.wangjj.bankperformance.Mapper.InstitutionMapper;
import org.wangjj.bankperformance.Service.IDutyService;
import org.wangjj.bankperformance.Service.IInstitutionService;
import org.wangjj.bankperformance.Service.IMenuService;

@Service
public class InstitutionServiceImpl implements IInstitutionService {
	
	@Autowired
	InstitutionMapper institutionMapper;

	@Override
	public List<Institution> getAllInstitution() {
		// TODO Auto-generated method stub
		return institutionMapper.selectAll();
	}

	@Override
	public Institution getInstitutionByInsId(String insId) {
		// TODO Auto-generated method stub
		return institutionMapper.selectByInsId(insId);
	}

	

}
