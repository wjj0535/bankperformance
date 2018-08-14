package org.wangjj.bankperformance.ServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wangjj.bankperformance.Entity.User;
import org.wangjj.bankperformance.Mapper.UserMapper;
import org.wangjj.bankperformance.Service.IUserService;

@Service
public class UserServiceImpl implements IUserService {
	
	private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	UserMapper userMapper;
	
	@Override
	public User getUserById(String userId) {
		// TODO Auto-generated method stub
		return userMapper.selectUserById(userId);
	}
	@Override
	public List<User> selectAllUser() {
		// TODO Auto-generated method stub
		return userMapper.selectAllUser();
	}

	@Override
	public boolean saveUser(User user) {
		// TODO Auto-generated method stub
		Boolean bRet = true;
		try {
			userMapper.insertUser(user);
		}
		catch(Exception e)
		{
			logger.error("保存用户异常", e);
			bRet = false;
		}
		return bRet;
	}

	@Override
	public boolean removeUserByUserId(String userId) {
		// TODO Auto-generated method stub
		Boolean bRet = true;
		try {
			userMapper.deleteUserByUserId(userId);
		}
		catch(Exception e)
		{
			logger.error("删除用户异常", e);
			bRet = false;
		}
		return bRet;
	}

	@Override
	public boolean modifyUser(User user) {
		// TODO Auto-generated method stub
		Boolean bRet = true;
		try {
			userMapper.updateUser(user);;
		}
		catch(Exception e)
		{
			logger.error("删除用户异常", e);
			bRet = false;
		}
		return bRet;
	}
	@Override
	public List<User> getUsersByDutyIdAndInsId(String dutyId, String insId) {
		// TODO Auto-generated method stub
		Map<String, String> param = new HashMap<>();
		param.put("dutyId", dutyId);
		param.put("insId", insId);
		return userMapper.selectUsersByDutyIdAndInsId(param);
	}
	@Override
	public boolean modifyPwd(String userId, String newPwd) {
		// TODO Auto-generated method stub
		Boolean bRet = true;
		try {
			userMapper.updatePwd(userId, newPwd);;
		}
		catch(Exception e)
		{
			logger.error("更新用户密码异常", e);
			bRet = false;
		}
		return bRet;
	}

	

	
	
}
