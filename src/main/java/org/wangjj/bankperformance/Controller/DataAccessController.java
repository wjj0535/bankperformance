package org.wangjj.bankperformance.Controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.wangjj.bankperformance.Bean.QueryConditions;
import org.wangjj.bankperformance.Bean.QueryResult;
import org.wangjj.bankperformance.Bean.ResponseResult;
import org.wangjj.bankperformance.Constant.ConstantData;
import org.wangjj.bankperformance.Entity.Dangliang;
import org.wangjj.bankperformance.Entity.GaoGuanYDKaoHe;
import org.wangjj.bankperformance.Entity.JiGouNDKaoHe;
import org.wangjj.bankperformance.Entity.JiGouYDKaoHe;
import org.wangjj.bankperformance.Entity.JiangJinChi;
import org.wangjj.bankperformance.Entity.NianDuKaoHe;
import org.wangjj.bankperformance.Entity.TotalZL;
import org.wangjj.bankperformance.Entity.User;
import org.wangjj.bankperformance.Entity.YueDuKaoHe;
import org.wangjj.bankperformance.Enum.ERROR_CODE;
import org.wangjj.bankperformance.Enum.YG_LEVEL;
import org.wangjj.bankperformance.Service.IDangliangService;
import org.wangjj.bankperformance.Service.IDutyService;
import org.wangjj.bankperformance.Service.IGaoGuanYDKaoHeService;
import org.wangjj.bankperformance.Service.IJiGouNDKaoHeService;
import org.wangjj.bankperformance.Service.IJiGouYDKaoHeService;
import org.wangjj.bankperformance.Service.IJiangJinChiService;
import org.wangjj.bankperformance.Service.INianDuKaoHeService;
import org.wangjj.bankperformance.Service.IUserService;
import org.wangjj.bankperformance.Service.IYueDuKaoHeService;
import org.wangjj.bankperformance.ServiceImpl.JiGouNDKaoHeServiceImpl;
import org.wangjj.bankperformance.ServiceImpl.JiGouYDKaoHeServiceImpl;
import org.wangjj.bankperformance.Thread.UpdateYgYdKhJgWork;
import org.wangjj.bankperformance.Thread.WorkThread;
import org.wangjj.bankperformance.Utils.CalUtil;
import org.wangjj.bankperformance.Utils.CommonUtil;

import com.alibaba.fastjson.JSON;

@RestController
public class DataAccessController {
	
	@Autowired
	IYueDuKaoHeService yueDuKaoHeService;
	@Autowired
	INianDuKaoHeService nianDuKaoHeService;
	@Autowired
	IUserService userSerice;
	@Autowired
	IDangliangService dangliangService;
	@Autowired
	IJiGouYDKaoHeService jiGouYDKaoHeService;
	@Autowired
	IGaoGuanYDKaoHeService gaoGuanYDKaoHeService;
	@Autowired
	IJiangJinChiService jiangJinChiService;
	@Autowired
	IDutyService dutyService;
	@Autowired
	IDangliangService dangLiangService;
	@Autowired
	IJiGouNDKaoHeService jiGouNDKaoHeService;
	
	@PostMapping(value="/putYueDuKaoHe", consumes="application/json;charset=utf-8", produces="application/json;charset=utf-8")
	public ResponseResult insertYuedukaohe(@RequestBody List<YueDuKaoHe> listYdkh)
	{
		ResponseResult rr = new ResponseResult();
		//yueDuKaoHeService.insert(listYdkh);
		return rr;
	}
	
	@GetMapping(value="/getUser/{userId}", produces="application/json;charset=utf-8")
	public ResponseResult getUser(@PathVariable String userId)
	{
		ResponseResult rr = new ResponseResult();
		User user = userSerice.getUserById(userId);
		if(user != null)
		{
			rr.setCode(ERROR_CODE.SUCCESS.getCode());
			rr.setMsg(ERROR_CODE.SUCCESS.getMsg());
			QueryResult<User> qr = new QueryResult<>();
			qr.setSize("1");
			qr.getDataSet().add(user);
			rr.setResponseData(qr);
		}
		else
		{
			rr.setCode(ERROR_CODE.FAIL.getCode());
			rr.setMsg(ERROR_CODE.FAIL.getMsg());
		}
		return rr;
	}
	@PostMapping(value="/putUser", consumes="application/json;charset=utf-8", produces="application/json;charset=utf-8")
	public ResponseResult putUser(@RequestBody User user)
	{
		ResponseResult rr = new ResponseResult();
		user.setPassWd("123456");
		if(userSerice.saveUser(user))
		{
			rr.setCode(ERROR_CODE.SUCCESS.getCode());
			rr.setMsg(ERROR_CODE.SUCCESS.getMsg());
		}
		else
		{
			rr.setCode(ERROR_CODE.FAIL.getCode());
			rr.setMsg(ERROR_CODE.FAIL.getMsg());
		}
		return rr;
	}
	@PostMapping(value="/removeUser/{userId}", produces="application/json;charset=utf-8")
	public ResponseResult removeUser(@PathVariable String userId)
	{
		ResponseResult rr = new ResponseResult();
		if(userSerice.removeUserByUserId(userId))
		{
			rr.setCode(ERROR_CODE.SUCCESS.getCode());
			rr.setMsg(ERROR_CODE.SUCCESS.getMsg());
		}
		else
		{
			rr.setCode(ERROR_CODE.FAIL.getCode());
			rr.setMsg(ERROR_CODE.FAIL.getMsg());
		}
		return rr;
	}
	@PostMapping(value="/modifyUser", consumes="application/json;charset=utf-8", produces="application/json;charset=utf-8")
	public ResponseResult modifyUser(@RequestBody User user)
	{
		ResponseResult rr = new ResponseResult();
		if(userSerice.modifyUser(user))
		{
			rr.setCode(ERROR_CODE.SUCCESS.getCode());
			rr.setMsg(ERROR_CODE.SUCCESS.getMsg());
		}
		else
		{
			rr.setCode(ERROR_CODE.FAIL.getCode());
			rr.setMsg(ERROR_CODE.FAIL.getMsg());
		}
		return rr;
	}
	@GetMapping(value="/getNdkeList/{dutyId}/{year}", produces="application/json;charset=utf-8")
	public ResponseResult getNdKhListForPut(@PathVariable String dutyId, @PathVariable String year,HttpSession session)
	{
		ResponseResult rr = new ResponseResult();
		User user = (User)session.getAttribute(ConstantData.SESSION_ID);
		
		Calendar calendar = Calendar.getInstance();
		String curYear = String.valueOf(calendar.get(Calendar.YEAR));
		String insId = user.getInsId(); 
		NianDuKaoHe ndkh = new NianDuKaoHe();
		ndkh.setDutyId(dutyId);
		ndkh.setYear(year);
		List<NianDuKaoHe> ndkhList = null;
		List<NianDuKaoHe> prevNdkhList = null;
		int nYear = Integer.valueOf(year);
		String lastYear = String.valueOf(nYear-1);
		if(user.getDutyId().equals("18"))  //支行打分员获取列表
		{
			ndkh.setInsId(insId);
			//查询所属年份的列表
			ndkhList = nianDuKaoHeService.getCurInsDutyYearUsers(ndkh);
			//查询去年的列表，获取去年的存款基数
			ndkh.setYear(lastYear);
			//得到去年的考核基数
			prevNdkhList = nianDuKaoHeService.getCurInsDutyYearUsers(ndkh);
		}
		else
		{
			ndkh.setInsId(insId);
			//查询所属年份的列表
			ndkhList = nianDuKaoHeService.getByYearAndDutyId(ndkh);
			//查询去年的列表，获取去年的存款基数
			ndkh.setYear(lastYear);
			//得到去年的考核基数
			prevNdkhList = nianDuKaoHeService.getByYearAndDutyId(ndkh);
		}
		Map<String, String> map_PrevCkjs = new HashMap<>();
		for(NianDuKaoHe item:prevNdkhList)
		{
			map_PrevCkjs.put(item.getUserId(), item.getCkjs());
		}
		//得到去年的实际存款数
		List<TotalZL> zl = yueDuKaoHeService.getTotalByDutyIdAndYear(dutyId, lastYear);
		Map<String, String> map_zl = new HashMap<>();
		for(TotalZL item:zl)
		{
			map_zl.put(item.getUserId(), item.getZongLiang());
		}
		for(int i=0; i<ndkhList.size(); i++)
		{
			NianDuKaoHe temp = ndkhList.get(i);
			temp.setYear(curYear);
			if(temp.getPrevCkjs() == null || "".equals(temp.getPrevCkjs()))
			{
				temp.setPrevCkjs(map_PrevCkjs.get(ndkhList.get(i).getUserId()));
			}
			if(temp.getLastZL() == null || "".equals(temp.getLastZL()))
			{
				temp.setLastZL(map_zl.get(ndkhList.get(i).getUserId()));
			}
		}
		rr.setCode(ERROR_CODE.SUCCESS.getCode());
		rr.setMsg(ERROR_CODE.SUCCESS.getMsg());
		QueryResult<NianDuKaoHe> qr = new QueryResult<>();
		qr.setSize(String.valueOf(ndkhList.size()));
		qr.setDataSet(ndkhList);
		rr.setResponseData(qr);
		return rr;
	}
	@PostMapping(value="/putNdKe", consumes="application/json;charset=utf-8", produces="application/json;charset=utf-8")
	public ResponseResult putNdKh(@RequestBody NianDuKaoHe ndkh, HttpSession session)
	{
		boolean bRet = false;
		ResponseResult rr = new ResponseResult();
		User user = (User)userSerice.getUserById(ndkh.getUserId());
		NianDuKaoHe t_ndkh = nianDuKaoHeService.getByUserIdAndYear(ndkh);
		ndkh.setInsId(user.getInsId());
		ndkh.setDutyId(user.getDutyId());
		if(t_ndkh == null)
		{
			bRet = nianDuKaoHeService.insert(ndkh);
		}
		else
		{
			bRet = nianDuKaoHeService.modifyByUserIdAndYear(ndkh);
		}
		if(bRet)
		{
			rr.setCode(ERROR_CODE.SUCCESS.getCode());
			rr.setMsg(ERROR_CODE.SUCCESS.getMsg());
		}
		else
		{
			rr.setCode(ERROR_CODE.FAIL.getCode());
			rr.setMsg(ERROR_CODE.FAIL.getMsg());
		}
		return rr;
	}
	@GetMapping(value="/getYdkeList/{insId}/{dutyId}/{yearMonth}", produces="application/json;charset=utf-8")
	public ResponseResult getYdkhListForPut(@PathVariable String insId, 
										@PathVariable String dutyId, 
										@PathVariable String yearMonth,
										HttpSession session)
	{
		ResponseResult rr = new ResponseResult();
		YueDuKaoHe ydkh = new YueDuKaoHe();
		List<YueDuKaoHe> ydkhList = null;
		int nDutyId = Integer.valueOf(dutyId);
		//支行长、部门经理
		if(nDutyId >= 9 && nDutyId <= 12 || nDutyId == 15)
		{
			ydkh.setDutyId(dutyId);
			ydkh.setYearMonth(yearMonth);
			//查询所属年份的列表
			ydkhList = yueDuKaoHeService.getByDutyIdAndYearMonth(ydkh);
		}
		else //普通员工
		{
			ydkh.setDutyId(dutyId);
			ydkh.setInsId(insId);
			//ndkh.setYear(curYear);
			ydkh.setYearMonth(yearMonth);
			//查询所属年份的列表
			ydkhList = yueDuKaoHeService.getCurInsDutyDateUsers(ydkh);
		}
		for(YueDuKaoHe item : ydkhList)
		{
			item.setYearMonth(yearMonth);
		}
		rr.setCode(ERROR_CODE.SUCCESS.getCode());
		rr.setMsg(ERROR_CODE.SUCCESS.getMsg());
		QueryResult<YueDuKaoHe> qr = new QueryResult<>();
		qr.setSize(String.valueOf(ydkhList.size()));
		qr.setDataSet(ydkhList);
		rr.setResponseData(qr);
		return rr;
	}
	@PostMapping(value="/putYdKe", consumes="application/json;charset=utf-8", produces="application/json;charset=utf-8")
	public ResponseResult putYdKh(@RequestBody YueDuKaoHe ydkh, HttpSession session)
	{
		boolean bRet = false;
		ResponseResult rr = new ResponseResult();
		//查询当年奖金池是否设置
		String year = ydkh.getYearMonth().substring(0, 4);
		JiangJinChi jjc = jiangJinChiService.getByDutyIdAndYear(ydkh.getDutyId(), year);
		if(jjc == null || jjc.getTotalMoney() == null || "".equals(jjc.getTotalMoney()))
		{
			rr.setCode(ERROR_CODE.NOT_SET_JJC.getCode());
			rr.setMsg(ERROR_CODE.NOT_SET_JJC.getMsg());
			return rr;
		}
		
		User user = (User)userSerice.getUserById(ydkh.getUserId());
		//查询所属机构是否打分
		JiGouYDKaoHe qjgydkh = new JiGouYDKaoHe();
		qjgydkh.setInsId(user.getInsId());
		qjgydkh.setYearMonth(ydkh.getYearMonth());
		JiGouYDKaoHe jgydkh = jiGouYDKaoHeService.getByInsIdAndYearMonth(qjgydkh);
		if(jgydkh == null || jgydkh.getFpxs() == null || "".equals(jgydkh.getFpxs()))
		{
			rr.setCode(ERROR_CODE.NOT_JG_FPXS.getCode());
			rr.setMsg(ERROR_CODE.NOT_JG_FPXS.getMsg());
			return rr;
		}
		//查询年度考核基数是否设置
		NianDuKaoHe queryParam = new NianDuKaoHe();
		queryParam.setUserId(user.getUserId());
		queryParam.setYear(year);
		NianDuKaoHe ndkh = nianDuKaoHeService.getByUserIdAndYear(queryParam);
		if(ndkh == null || ndkh.getCkjs() == null || "".equals(ndkh.getCkjs()))
		{
			rr.setCode(ERROR_CODE.NOT_SET_NDJS.getCode());
			rr.setMsg(ERROR_CODE.NOT_SET_NDJS.getMsg());
			return rr;
		}
		YueDuKaoHe t_ydkh = yueDuKaoHeService.getByUserIdAndDate(ydkh);
		ydkh.setInsId(user.getInsId());
		ydkh.setDutyId(user.getDutyId());
		//计算各种当量
		float cunkuan = Float.valueOf(ydkh.getCunKuan());
		float daikuan = Float.valueOf(ydkh.getDaiKuan());
		float buliangdaikuan = Float.valueOf(ydkh.getBuLiangDaiKuan());
		float dangliang = 0.0f;
		//传过来的value 表示数量
		List<Dangliang> dl = JSON.parseArray(ydkh.getOther(), Dangliang.class);
		for(Dangliang item : dl)
		{
			Dangliang dbdl = dangLiangService.getByCode(item.getCode());
			float dbval = Float.valueOf(dbdl.getValue());
			float formval = Float.valueOf(item.getValue());
			dangliang += dbval*formval;
		}
		//计算总量
		float zongliang = cunkuan + daikuan - 5*buliangdaikuan + dangliang;
		ydkh.setZongLiang(zongliang);
		//计算该员工的存款考核分数
		float thisYearCkjs = Float.valueOf(ndkh.getCkjs());
		float lastCkjs = Float.valueOf(ndkh.getPrevCkjs());
		float lastZL = Float.valueOf(ndkh.getLastZL());
		int month = Integer.valueOf(ydkh.getYearMonth().substring(4, 6));
		//截止到当前月为止，应完成存款数
		float nowCkjs = thisYearCkjs*month/12;
		TotalZL zl = yueDuKaoHeService.getTotalByUserIdAndYear(user.getUserId(), year);
		//截止到当前月为止，实际完成存款额
		float actualNowCk = 0.0f;
		if(zl != null)
			actualNowCk = Float.valueOf(zl.getZongLiang());
		//存款新增考核基数
		float xzJs = (thisYearCkjs - lastCkjs)*month/12;
		float actualXz = actualNowCk - lastZL*month/12;
		float ck = 0.0f;
		if(nowCkjs != 0)
			ck = actualNowCk*0.7f/nowCkjs;
		float xz = 0f;
		if(xzJs != 0)
			xz = actualXz*0.3f/xzJs;
		float score = ck + xz;
		ydkh.setScore(score);
		ydkh.setYearMonth(ydkh.getYearMonth());
		
		if(t_ydkh == null)
		{
			bRet = yueDuKaoHeService.insert(ydkh);
		}
		else
		{
			bRet = yueDuKaoHeService.modifyByUserIdAndDate(ydkh);
		}
		if(bRet)
		{
			rr.setCode(ERROR_CODE.SUCCESS.getCode());
			rr.setMsg(ERROR_CODE.SUCCESS.getMsg());
			UpdateYgYdKhJgWork work = new UpdateYgYdKhJgWork(ydkh.getDutyId(), ydkh.getYearMonth());
			//后台对该序列重新排序
			WorkThread.putWork(work);
		}
		else
		{
			rr.setCode(ERROR_CODE.FAIL.getCode());
			rr.setMsg(ERROR_CODE.FAIL.getMsg());
		}
		return rr;
	}
	@GetMapping(value="/getDl", produces="application/json;charset=utf-8")
	public ResponseResult getDl( HttpSession session)
	{
		ResponseResult rr = new ResponseResult();
		QueryResult<Dangliang> qr = new QueryResult<Dangliang>();
		
		List<Dangliang> dl = dangliangService.getAll();
		
		rr.setCode(ERROR_CODE.SUCCESS.getCode());
		rr.setMsg(ERROR_CODE.SUCCESS.getMsg());
		qr.setSize(String.valueOf(dl.size()));
		qr.setDataSet(dl);
		rr.setResponseData(qr);
		return rr;
	}
	@PostMapping(value="/putDl", consumes="application/json;charset=utf-8", produces="application/json;charset=utf-8")
	public ResponseResult putDl(@RequestBody List<Dangliang> dataList, HttpSession session)
	{
		ResponseResult rr = new ResponseResult();
		for(Dangliang dl : dataList)
		{
			Dangliang qdl = dangliangService.getByCode(dl.getCode());
			if(qdl == null)
			{
				dangLiangService.insert(dl);
			}
			else
				dangliangService.modifyValueByCode(dl);
		}
		rr.setCode(ERROR_CODE.SUCCESS.getCode());
		rr.setMsg(ERROR_CODE.SUCCESS.getMsg());
		return rr;
	}
	@PostMapping(value="/getJgKaoHeList", consumes="application/json;charset=utf-8", produces="application/json;charset=utf-8")
	public ResponseResult getJgKaoHeListForPut(@RequestBody JiGouYDKaoHe jgydkh, HttpSession session)
	{
		ResponseResult rr = new ResponseResult();
		QueryResult<JiGouYDKaoHe> qr = new QueryResult<JiGouYDKaoHe>();
		List<JiGouYDKaoHe> dl = jiGouYDKaoHeService.getByTypeAndYearMonth(jgydkh);
		if(dl != null && dl.size() > 0)
		{
			for(JiGouYDKaoHe item:dl)
			{
				item.setYearMonth(jgydkh.getYearMonth());
			}
		}
			
		rr.setCode(ERROR_CODE.SUCCESS.getCode());
		rr.setMsg(ERROR_CODE.SUCCESS.getMsg());
		qr.setSize(String.valueOf(dl.size()));
		qr.setDataSet(dl);
		rr.setResponseData(qr);
		return rr;
	}
	@PostMapping(value="/putJgKaoHe", consumes="application/json;charset=utf-8", produces="application/json;charset=utf-8")
	public ResponseResult putJgKaoHe(@RequestBody JiGouYDKaoHe jgydkh, HttpSession session)
	{
		ResponseResult rr = new ResponseResult();
		//根据details计算分数
		
		//
		JiGouYDKaoHe t_jgkh = jiGouYDKaoHeService.getByInsIdAndYearMonth(jgydkh);
		if(t_jgkh.getScore() == 0.0f)
		{
			if(!jiGouYDKaoHeService.insert(jgydkh))
			{
				rr.setCode(ERROR_CODE.FAIL.getCode());
				rr.setMsg(ERROR_CODE.FAIL.getMsg());
				return rr;
			}
		}
		else 
		{
			if(!jiGouYDKaoHeService.modifyByInsIdAndYearMonth(jgydkh))
			{
				rr.setCode(ERROR_CODE.FAIL.getCode());
				rr.setMsg(ERROR_CODE.FAIL.getMsg());
				return rr;
			}
		}
		rr.setCode(ERROR_CODE.SUCCESS.getCode());
		rr.setMsg(ERROR_CODE.SUCCESS.getMsg());
		//计算年度考核分数并更新
		String year = jgydkh.getYearMonth().substring(0, 4);
		JiGouYDKaoHe qjgydkh = new JiGouYDKaoHe();
		qjgydkh.setInsId(jgydkh.getInsId());
		qjgydkh.setYearMonth(year);
		qjgydkh.setIsYuanJiao(jgydkh.getIsYuanJiao());
		List<JiGouYDKaoHe> list_jgydkh = jiGouYDKaoHeService.getByInsIdAndYear(qjgydkh);
		float average_score = 0;
		for(JiGouYDKaoHe item : list_jgydkh)
		{
			average_score += item.getScore();
		}
		average_score/=list_jgydkh.size();
		JiGouNDKaoHe jgndkh = new JiGouNDKaoHe();
		jgndkh.setInsId(jgydkh.getInsId());
		jgndkh.setInsName(jgydkh.getInsName());
		jgndkh.setYear(year);
		jgndkh.setScore(average_score);
		//先删除再增加
		jiGouNDKaoHeService.deleteByInsIdAndYear(jgndkh);
		jiGouNDKaoHeService.insert(jgndkh);
		
		//查看其它机构是否已经打完分
		List<JiGouYDKaoHe> list = jiGouYDKaoHeService.getByTypeAndYearMonth(jgydkh);
		boolean canSort = true;
		for(JiGouYDKaoHe item:list)
		{
			if(item.getScore() == 0.0f)
			{
				canSort = false;
				break;
			}
		}
		//重新排名
		if(canSort)
		{
			List<Integer> sortNum = CalUtil.getPMCount(list.size());
			int begin = 0;
			int rank = 0;
			for(int i=0; i<sortNum.size(); i++)
			{
				int size = sortNum.get(i).intValue();
				String level = "";
				float fpxs = 0.0f;					
				switch(i)
				{
				case 0:
					level = YG_LEVEL.A_PLUS.getCode();
					fpxs = YG_LEVEL.A_PLUS.getFpxs();
					break;
				case 1:
					level = YG_LEVEL.A.getCode();
					fpxs = YG_LEVEL.A.getFpxs();
					break;
				case 2:
					level = YG_LEVEL.B_PLUS.getCode();
					fpxs = YG_LEVEL.B_PLUS.getFpxs();
					break;
				case 3:
					level = YG_LEVEL.B.getCode();
					fpxs = YG_LEVEL.B.getFpxs();
					break;
				case 4:
					level = YG_LEVEL.C.getCode();
					fpxs = YG_LEVEL.C.getFpxs();
					break;
				}
				for(int j=0; j<sortNum.get(i); j++)
				{
					JiGouYDKaoHe item = list.get(j+begin);
					item.setRank(++rank);
					item.setLevel(level);
					item.setFpxs(String.valueOf(fpxs));
					jiGouYDKaoHeService.setKhjgByInsIdAndYearMonth(item);
				}
				begin += size;
			}
		}
		return rr;
	}
	@PostMapping(value="/getGgKaoHeList", consumes="application/json;charset=utf-8", produces="application/json;charset=utf-8")
	public ResponseResult getGgKaoHeList(@RequestBody GaoGuanYDKaoHe ggydkh, HttpSession session)
	{
		ResponseResult rr = new ResponseResult();
		QueryResult<GaoGuanYDKaoHe> qr = new QueryResult<GaoGuanYDKaoHe>();
		List<GaoGuanYDKaoHe> dl = gaoGuanYDKaoHeService.getByDutyAndYearMonth(ggydkh);
		if(dl != null && dl.size() > 0)
		{
			for(GaoGuanYDKaoHe item:dl)
			{
				item.setYearMonth(ggydkh.getYearMonth());
			}
		}
			
		rr.setCode(ERROR_CODE.SUCCESS.getCode());
		rr.setMsg(ERROR_CODE.SUCCESS.getMsg());
		qr.setSize(String.valueOf(dl.size()));
		qr.setDataSet(dl);
		rr.setResponseData(qr);
		return rr;
	}
	@PostMapping(value="/getYgYdKhjg", consumes="application/json;charset=utf-8", produces="application/json;charset=utf-8")
	public ResponseResult getYgYdKhjg(@RequestBody QueryConditions qc, HttpSession session)
	{
		ResponseResult rr = new ResponseResult();
		if(qc.getDutyId().startsWith("a"))
		{
			
		}
		else
		{
			QueryResult<YueDuKaoHe> qr = new QueryResult<YueDuKaoHe>();
			YueDuKaoHe ydkh = new YueDuKaoHe();
			ydkh.setXulieId(CommonUtil.getXulieIdByDutyId(qc.getDutyId()));
			ydkh.setYearMonth(qc.getYearMonth());
			List<YueDuKaoHe> ydkhList = yueDuKaoHeService.getByDateAndXulie(ydkh);
			qr.setSize(String.valueOf(ydkhList.size()));
			qr.setDataSet(ydkhList);
			rr.setResponseData(qr);
		}
		
		rr.setCode(ERROR_CODE.SUCCESS.getCode());
		rr.setMsg(ERROR_CODE.SUCCESS.getMsg());
		return rr;
	}
	@PostMapping(value="/getYgNdKhjg", consumes="application/json;charset=utf-8", produces="application/json;charset=utf-8")
	public ResponseResult getYgNdKhjg(@RequestBody QueryConditions qc, HttpSession session)
	{
		ResponseResult rr = new ResponseResult();
		return rr;
	}
	@PostMapping(value="/getJgNdKhjg", consumes="application/json;charset=utf-8", produces="application/json;charset=utf-8")
	public ResponseResult getJgNdKhjg(@RequestBody QueryConditions qc, HttpSession session)
	{
		ResponseResult rr = new ResponseResult();
		JiGouNDKaoHe qjgndkh = new JiGouNDKaoHe();
		qjgndkh.setYear(qc.getYear());
		qjgndkh.setIsYuanJiao(qc.getIsYuanJiao());
		List<JiGouNDKaoHe> list_jgndkh = jiGouNDKaoHeService.getByTypeAndYear(qjgndkh);
		rr.setCode(ERROR_CODE.SUCCESS.getCode());
		rr.setMsg(ERROR_CODE.SUCCESS.getMsg());
		QueryResult<JiGouNDKaoHe> qr = new QueryResult<JiGouNDKaoHe>();
		//计算排名
		//判断是否已经进行排序
		List<Integer> sortNum = CalUtil.getPMCount(list_jgndkh.size());
		int begin = 0;
		int rank = 0;
		for(int i=0; i<sortNum.size(); i++)
		{
			int size = sortNum.get(i).intValue();
			String level = "";
			float fpxs = 0.0f;
			switch(i)
			{
			case 0:
				level = YG_LEVEL.A_PLUS.getCode();
				fpxs = YG_LEVEL.A_PLUS.getFpxs();
				break;
			case 1:
				level = YG_LEVEL.A.getCode();
				fpxs = YG_LEVEL.A_PLUS.getFpxs();
				break;
			case 2:
				level = YG_LEVEL.B_PLUS.getCode();
				fpxs = YG_LEVEL.A_PLUS.getFpxs();
				break;
			case 3:
				level = YG_LEVEL.B.getCode();
				fpxs = YG_LEVEL.A_PLUS.getFpxs();
				break;
			case 4:
				level = YG_LEVEL.C.getCode();
				fpxs = YG_LEVEL.A_PLUS.getFpxs();
				break;
			}
			for(int j=0; j<sortNum.get(i); j++)
			{
				JiGouNDKaoHe item = list_jgndkh.get(j+begin);
				item.setRank(++rank);
				item.setLevel(level);
				item.setYear(qc.getYear());
				item.setFpxs(String.valueOf(fpxs));
				jiGouNDKaoHeService.setKhjgByInsIdAndYear(item);
			}
			begin += size;
		}
		qr.setSize(String.valueOf(list_jgndkh.size()));
		qr.setDataSet(list_jgndkh);
		rr.setResponseData(qr);
		return rr;
	}
	@PostMapping(value="/putJjc", consumes="application/json;charset=utf-8", produces="application/json;charset=utf-8")
	public ResponseResult putJjc(@RequestBody JiangJinChi jjc, HttpSession session)
	{
		ResponseResult rr = new ResponseResult();
		if(jiangJinChiService.insert(jjc)) 
		{
			rr.setCode(ERROR_CODE.SUCCESS.getCode());
			rr.setMsg(ERROR_CODE.SUCCESS.getMsg());
		}
		else
		{
			rr.setCode(ERROR_CODE.FAIL.getCode());
			rr.setMsg(ERROR_CODE.FAIL.getMsg());
		}
		
		return rr;
	}
	@PostMapping(value="/modifyJjc", consumes="application/json;charset=utf-8", produces="application/json;charset=utf-8")
	public ResponseResult modifyJjc(@RequestBody JiangJinChi jjc, HttpSession session)
	{
		ResponseResult rr = new ResponseResult();
		if(jiangJinChiService.modifyByYearAndDutyId(jjc)) //查询高管的考核结果
		{
			rr.setCode(ERROR_CODE.SUCCESS.getCode());
			rr.setMsg(ERROR_CODE.SUCCESS.getMsg());
		}
		else
		{
			rr.setCode(ERROR_CODE.FAIL.getCode());
			rr.setMsg(ERROR_CODE.FAIL.getMsg());
		}
		
		return rr;
	}
	@PostMapping(value="/removeJjc", consumes="application/json;charset=utf-8", produces="application/json;charset=utf-8")
	public ResponseResult removeJjc(@RequestBody JiangJinChi jjc, HttpSession session)
	{
		ResponseResult rr = new ResponseResult();
		if(jiangJinChiService.removeByYearAndDutyId(jjc)) //查询高管的考核结果
		{
			rr.setCode(ERROR_CODE.SUCCESS.getCode());
			rr.setMsg(ERROR_CODE.SUCCESS.getMsg());
		}
		else
		{
			rr.setCode(ERROR_CODE.FAIL.getCode());
			rr.setMsg(ERROR_CODE.FAIL.getMsg());
		}
		
		return rr;
	}
	@GetMapping(value="/getJjcList/{year}", produces="application/json;charset=utf-8")
	public ResponseResult getJjcList(@PathVariable String year, HttpSession session)
	{
		ResponseResult rr = new ResponseResult();
		QueryResult<JiangJinChi> qr = new QueryResult<>();
		List<JiangJinChi> retList = jiangJinChiService.getByYear(year);
		for(JiangJinChi jjc : retList)
		{
			StringBuilder sb = new StringBuilder();
			String[] szId = jjc.getDutyId().split(",");
			for(String id: szId)
			{
				String dutyName = dutyService.getById(id).getName();
				sb.append(dutyName);
				sb.append(",");
			}
			jjc.setDutyName(sb.toString().substring(0, sb.toString().length()-1));
		}
		qr.setDataSet(retList);
		qr.setSize(String.valueOf(retList.size()));
		rr.setResponseData(qr);
		rr.setCode(ERROR_CODE.SUCCESS.getCode());
		rr.setMsg(ERROR_CODE.SUCCESS.getMsg());
		return rr;
	}
	@PostMapping(value="/modifypwd", produces="application/json;charset=utf-8")
	public ResponseResult modifypwd(@RequestParam(value="edit_oldPwd") String oldPwd, 
									@RequestParam(value="edit_newPwd") String newPwd,
									HttpSession session)
	{
		ResponseResult rr = new ResponseResult();
		User user = (User)session.getAttribute(ConstantData.SESSION_ID);
		if(!user.getPassWd().equals(oldPwd))
		{
			rr.setCode(ERROR_CODE.PWD_ERROR.getCode());
			rr.setMsg(ERROR_CODE.PWD_ERROR.getMsg());
		}
		else
		{
			if(userSerice.modifyPwd(user.getUserId(), newPwd))
			{
				rr.setCode(ERROR_CODE.SUCCESS.getCode());
				rr.setMsg(ERROR_CODE.SUCCESS.getMsg());
				user.setPassWd(newPwd);
			}
			else
			{
				rr.setCode(ERROR_CODE.MODIFY_PWD_ERROR.getCode());
				rr.setMsg(ERROR_CODE.MODIFY_PWD_ERROR.getMsg());
			}
		}
		return rr;
	}
}
