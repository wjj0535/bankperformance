package org.wangjj.bankperformance.Service;

import java.util.List;

import org.wangjj.bankperformance.Entity.User;

public interface IUserService {
	
	public List<User> selectAllUser();
	
	public List<User> getUsersByDutyIdAndInsId(String dutyId, String insId);
	
	public User getUserById(String userId);
	
	public boolean saveUser(User user);
	
	public boolean removeUserByUserId(String userId);
	
	public boolean modifyUser(User user);
	
	public boolean modifyPwd(String userId, String newPwd);
	
}
