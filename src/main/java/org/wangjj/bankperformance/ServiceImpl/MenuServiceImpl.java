package org.wangjj.bankperformance.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wangjj.bankperformance.Mapper.ChaxunMenuMapper;
import org.wangjj.bankperformance.Service.IMenuService;

@Service
public class MenuServiceImpl implements IMenuService {
	
	@Autowired
	ChaxunMenuMapper queryMenu;

	@Override
	public String getQueryMenu() {
		// TODO Auto-generated method stub
		return queryMenu.selectMenu();
	} 


}
