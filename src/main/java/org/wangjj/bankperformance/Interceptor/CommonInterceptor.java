package org.wangjj.bankperformance.Interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.wangjj.bankperformance.Bean.ResponseResult;
import org.wangjj.bankperformance.Constant.ConstantData;

public class CommonInterceptor implements HandlerInterceptor {

	
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		// TODO Auto-generated method stub
		ResponseResult rr = new ResponseResult();
		HttpSession session = arg0.getSession();
		//如果已登录
		if(session.getAttribute(ConstantData.SESSION_ID) != null)
		{
			return true;
		}
		//arg1.sendRedirect("index.html");
		arg1.reset();
		PrintWriter pw = arg1.getWriter();
		String js = "<script>var w=window.parent||window;w.location.href='/index.html';</script>";
		pw.write(js);
		pw.flush();
		pw.close();
		return false;
	}

}
