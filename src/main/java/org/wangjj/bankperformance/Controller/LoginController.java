package org.wangjj.bankperformance.Controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.wangjj.bankperformance.Bean.ResponseResult;
import org.wangjj.bankperformance.Constant.ConstantData;
import org.wangjj.bankperformance.Entity.User;
import org.wangjj.bankperformance.Enum.ERROR_CODE;
import org.wangjj.bankperformance.Mapper.UserMapper;
import org.wangjj.bankperformance.Service.IMenuService;

@RestController
public class LoginController {
	
	@Autowired
	IMenuService menuService;
	
	@Autowired
	UserMapper userService;
	
	@PostMapping(value="/login", consumes="application/json;charset=utf-8", produces="application/json;charset=utf-8")
	public ResponseResult login(@RequestBody User userInfo
								, HttpSession session)
	{
		ResponseResult rr = new ResponseResult();
		User user = userService.selectUserByAccount(userInfo.getUserAccount());
		if(user == null)
		{
			rr.setCode(ERROR_CODE.QUERY_USERINFO_ERROR.getCode());
			rr.setMsg(ERROR_CODE.QUERY_USERINFO_ERROR.getMsg());
		}
		else if(!user.getPassWd().equals(userInfo.getPassWd()))
		{
			rr.setCode(ERROR_CODE.PWD_ERROR.getCode());
			rr.setMsg(ERROR_CODE.PWD_ERROR.getMsg());
		}
		else
		{
			session.setAttribute(ConstantData.SESSION_ID, user);
			rr.setCode(ERROR_CODE.SUCCESS.getCode());
			rr.setMsg(ERROR_CODE.SUCCESS.getMsg());
		}
		return rr;
	}
	@GetMapping(value="/logout", produces="application/json;charset=utf-8")
	public ResponseResult logout(HttpServletResponse response, HttpSession session)
	{
		ResponseResult rr = new ResponseResult();
		
		session.invalidate();
		rr.setCode(ERROR_CODE.SUCCESS.getCode());
		rr.setMsg(ERROR_CODE.SUCCESS.getMsg());
		
		try {
			response.sendRedirect("index.html");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rr;
	}
}
