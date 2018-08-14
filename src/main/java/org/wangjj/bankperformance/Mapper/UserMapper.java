package org.wangjj.bankperformance.Mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.wangjj.bankperformance.Entity.User;

@Mapper
public interface UserMapper {
	
	public User selectUserByAccount(String account);
	
	public User selectUserById(String userId);
	
	public List<User> selectAllUser();
	
	public List<User> selectUsersByDutyIdAndInsId(Map<String, String> param);
	
	public void insertUser(User user);
	
	public void deleteUserByUserId(String userId);
	
	public void updateUser(User user);
	
	public void updatePwd(String userId, String newPwd);
	
}
