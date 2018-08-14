package org.wangjj.bankperformance.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.wangjj.bankperformance.Entity.MenuInfo;

@Mapper
public interface KaoHeMenuMapper {

	public List<MenuInfo> selectAllMenu();
	public List<MenuInfo> selectSomeMenu(List<String> menuList);
}
