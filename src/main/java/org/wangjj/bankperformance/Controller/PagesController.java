package org.wangjj.bankperformance.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.wangjj.bankperformance.Constant.ConstantData;
import org.wangjj.bankperformance.Entity.Duty;
import org.wangjj.bankperformance.Entity.Institution;
import org.wangjj.bankperformance.Entity.MenuInfo;
import org.wangjj.bankperformance.Entity.NianDuKaoHe;
import org.wangjj.bankperformance.Entity.User;
import org.wangjj.bankperformance.Mapper.KaoHeMenuMapper;
import org.wangjj.bankperformance.Service.IDutyService;
import org.wangjj.bankperformance.Service.IInstitutionService;
import org.wangjj.bankperformance.Service.IJiangJinChiService;
import org.wangjj.bankperformance.Service.INianDuKaoHeService;
import org.wangjj.bankperformance.Service.IUserService;
import org.wangjj.bankperformance.Utils.CommonUtil;

import com.alibaba.fastjson.JSON;

@Controller
public class PagesController {
	
	@Autowired
	KaoHeMenuMapper kaoHeMenuMapper;
	
	@Autowired
	IUserService userServiceImpl;
	
	@Autowired
	IDutyService dutyServiceImpl;
	
	@Autowired
	IInstitutionService institutionServiceImpl;
	
	@Autowired
	INianDuKaoHeService niandukaoheServerImpl;
	
	@Autowired
	IJiangJinChiService jiangJinChiService;
	
	@GetMapping(value="/{pageName}.html")
	public String commonPages(@PathVariable(name="pageName") String pageName, Model model)
	{
		return pageName;
	}
	@GetMapping(value="/mainframe.html")
	public String mainFrame(Model model, HttpSession session)
	{
		User user = (User)session.getAttribute(ConstantData.SESSION_ID);
		model.addAttribute("user", user);
		List<String> menuList =  Arrays.asList(user.getMenuList().split(","));
//		List<String> menuList = new ArrayList<>();
//		menuList.add("1");
//		menuList.add("2");
		List<MenuInfo> kaoHeMenuList = kaoHeMenuMapper.selectSomeMenu(menuList);
		model.addAttribute("kaoHeMenuList", JSON.toJSONString(kaoHeMenuList));
		return "mainframe";
	}
	@GetMapping(value="/yuanGongManager.html")
	public String yuanGongManager(Model model, HttpSession session)
	{
		List<User> ret = userServiceImpl.selectAllUser();
		for(User user:ret)
		{
			if(user.getSex().equals("nan"))
				user.setSex("男");
			else
				user.setSex("女");
		}
		model.addAttribute("userList", JSON.toJSONString(ret));
		List<Duty> dutyList = dutyServiceImpl.getAllDuty();
		model.addAttribute("dutyList", dutyList);
		List<Institution> insList = institutionServiceImpl.getAllInstitution();
		model.addAttribute("insList", insList);
		return "yuanGongManager";
	}
	@GetMapping(value="/kaohe/{dutyId}")
	public String kaohe(@PathVariable String dutyId, Model model, HttpSession session)
	{
		if("".equals(dutyId))
			return "";
		User user = (User)session.getAttribute(ConstantData.SESSION_ID);
		if(!Character.isDigit(dutyId.charAt(0))) //不是数字 说明是机构考核
		{
			if(dutyId.startsWith("a")) //机构
			{
				model.addAttribute("isYuanJiao", "");
				if(dutyId.equals("a"))
				{
					model.addAttribute("dutyName", "机构");
				}
				else
				{
					//此处得到的是机构的id，
					String insId = dutyId.substring(1);
					model.addAttribute("insId", insId);
					if(user.getDutyId().equals("17"))
					{
						String layoutData = CommonUtil.getLayoutDataByInsId(insId);
						Institution ins = institutionServiceImpl.getInstitutionByInsId(insId);
						model.addAttribute("insName", ins.getInsName());
						model.addAttribute("layoutData", layoutData);
						return "departmentKaohe";
					}
					else
					{
						Calendar calendar = Calendar.getInstance();
						String curYear = String.valueOf(calendar.get(Calendar.YEAR));
						List<String> yearList = new ArrayList<>();
						yearList.add(curYear);
						model.addAttribute("yearList", yearList);
						model.addAttribute("dutyId", "15");
						model.addAttribute("dutyName", "部门员工");
						//总行经办才能发布年度任务
						if(!user.getUserId().equals("30"))
							model.addAttribute("showNdkh", "0");
						else 
							model.addAttribute("showNdkh", "1");
						return "yuanGongKaoHe";
					}
				}
				
			}
			else if(dutyId.equals("b1"))  //城区
			{
				model.addAttribute("dutyName", "城区支行");
				model.addAttribute("isYuanJiao", "0");
			}
			else if(dutyId.equals("b2")) //远郊
			{
				model.addAttribute("dutyName", "远郊支行");
				model.addAttribute("isYuanJiao", "1");
			}
			return "jiGouKaoHe";
		}
		else
		{
			Duty duty = dutyServiceImpl.getById(dutyId);
			model.addAttribute("dutyName", duty.getName());
			int nDutyId = Integer.valueOf(dutyId);
			if(nDutyId <= 8)  //高管的
			{
				model.addAttribute("dutyId", dutyId);
				return "gaoGuanKaoHe";
			}
			else //员工考核
			{
				String insId = user.getInsId(); 
				Calendar calendar = Calendar.getInstance();
				String curYear = String.valueOf(calendar.get(Calendar.YEAR));
				List<String> yearList = new ArrayList<>();
				yearList.add(curYear);
				model.addAttribute("yearList", yearList);
				model.addAttribute("dutyId", dutyId);
				model.addAttribute("insId", insId);
				model.addAttribute("showNdkh", "1");
				return "yuanGongKaoHe";
			}
		}
		
	}
	@GetMapping(value="/query/{dutyId}")
	public String query(@PathVariable String dutyId, Model model, HttpSession session)
	{
		if("".equals(dutyId))
			return "";
		if(!Character.isDigit(dutyId.charAt(0))) //不是数字 说明是机构考核
		{
			if(dutyId.startsWith("a")) //机构
			{
				model.addAttribute("dutyName", "机构");
				model.addAttribute("isYuanJiao", "");
			}
			else if(dutyId.equals("b1"))  //城区
			{
				model.addAttribute("dutyName", "城区支行");
				model.addAttribute("isYuanJiao", "0");
			}
			else if(dutyId.equals("b2")) //远郊
			{
				model.addAttribute("dutyName", "远郊支行");
				model.addAttribute("isYuanJiao", "1");
			}
			List<String> yearList = new ArrayList<>();
			Calendar calen = Calendar.getInstance();
			for(int i=2018; i<=calen.get(Calendar.YEAR); i++)
			{
				yearList.add(String.valueOf(i));				
			}
			model.addAttribute("yearList", yearList);
			return "jgQueryResult";
		}
		else
		{
			Duty duty = dutyServiceImpl.getById(dutyId);
			model.addAttribute("dutyName", duty.getName());
			List<String> yearList = new ArrayList<>();
			Calendar calen = Calendar.getInstance();
			for(int i=2018; i<=calen.get(Calendar.YEAR); i++)
			{
				yearList.add(String.valueOf(i));				
			}
			model.addAttribute("yearList", yearList);
			int nDutyId = Integer.valueOf(dutyId);
			if(nDutyId <= 8)  //高管的
			{
				model.addAttribute("dutyId", dutyId);
				return "gaoGuanQueryResult";
			}
			else //员工考核
			{
				model.addAttribute("dutyId", dutyId);
				return "ygQueryResult";
			}
		}
	}
	@GetMapping(value="/jiangJinChi.html")
	public String jiangjinchi(Model model, HttpSession session)
	{
		Set<String> years = jiangJinChiService.getAllYears();
		Calendar cale = Calendar.getInstance();
		String curYear = String.valueOf(cale.get(Calendar.YEAR));
		years.add(curYear);
 		model.addAttribute("yearList", years);
 		Map<String, String> dutyList = new HashMap<>();
 		dutyList.put("9,10,", "支行行长、副行长");
 		dutyList.put("11,12,", "部门经理、副经理");
 		dutyList.put("13,", "柜员");
 		dutyList.put("14,", "客户经理");
 		dutyList.put("15,", "部门员工");
 		dutyList.put("16,", "劳务派遣");
 		model.addAttribute("dutyList", dutyList);
		return "jiangJinChi";
	}
}
