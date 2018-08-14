package org.wangjj.bankperformance.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.wangjj.bankperformance.Entity.MenuInfo;

@Mapper
public interface ChaxunMenuMapper {

	public List<MenuInfo> selectAllMenu();
	public String selectMenu();
}
