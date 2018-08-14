package org.wangjj.bankperformance.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wangjj.bankperformance.Entity.Duty;
import org.wangjj.bankperformance.Mapper.ChaxunMenuMapper;
import org.wangjj.bankperformance.Mapper.DutyMapper;
import org.wangjj.bankperformance.Service.IDutyService;
import org.wangjj.bankperformance.Service.IMenuService;

@Service
public class DutyServiceImpl implements IDutyService {
	
	@Autowired
	DutyMapper dutyMapper;

	@Override
	public List<Duty> getAllDuty() {
		// TODO Auto-generated method stub
		return dutyMapper.selectAllDuty();
	}

	@Override
	public Duty getById(String id) {
		// TODO Auto-generated method stub
		return dutyMapper.selectById(id);
	}


}
