package org.wangjj.bankperformance.ServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wangjj.bankperformance.Entity.TotalZL;
import org.wangjj.bankperformance.Entity.YueDuKaoHe;
import org.wangjj.bankperformance.Mapper.YueDuKaoHeMapper;
import org.wangjj.bankperformance.Service.IYueDuKaoHeService;

@Service
public class YueDuKaoHeServiceImpl implements IYueDuKaoHeService {

	private final static Logger logger = LoggerFactory.getLogger(YueDuKaoHeServiceImpl.class);

	@Autowired
	YueDuKaoHeMapper yueDuKaoHeMapper;

	@Override
	public boolean insert(YueDuKaoHe ydkh) {
		// TODO Auto-generated method stub
		Boolean bRet = true;
		try {
			yueDuKaoHeMapper.insert(ydkh);
		} catch (Exception e) {
			logger.error("保存月度考核数据异常", e);
			bRet = false;
		}
		return bRet;
	}

	@Override
	public YueDuKaoHe getByUserIdAndDate(YueDuKaoHe ydkh) {
		// TODO Auto-generated method stub
		return yueDuKaoHeMapper.selectByUserIdAndDate(ydkh);
	}

	@Override
	public List<YueDuKaoHe> getCurInsDutyDateUsers(YueDuKaoHe ydkh) {
		// TODO Auto-generated method stub
		return yueDuKaoHeMapper.selectByDateAndInsAndDuty(ydkh);
	}

	@Override
	public boolean modifyByUserIdAndDate(YueDuKaoHe ydkh) {
		// TODO Auto-generated method stub
		Boolean bRet = true;
		try {
			yueDuKaoHeMapper.updateByUserIdAndDate(ydkh);
		} catch (Exception e) {
			logger.error("保存年度考核数据异常", e);
			bRet = false;
		}
		return bRet;
	}

	@Override
	public List<YueDuKaoHe> getByDateAndXulie(YueDuKaoHe ydkh) {
		// TODO Auto-generated method stub
		return yueDuKaoHeMapper.selectByDateAndXulie(ydkh);
	}

	@Override
	public List<TotalZL> getTotalByDutyIdAndYear(String dutyId, String year) {
		// TODO Auto-generated method stub
		Map<String, String> param = new HashMap<>();
		param.put("dutyId", dutyId);
		param.put("likeCondition", year+"%");
		return yueDuKaoHeMapper.selectTotalByDutyIdAndYear(param);
	}

	@Override
	public TotalZL getTotalByUserIdAndYear(String userId, String year) {
		// TODO Auto-generated method stub
		Map<String, String> param = new HashMap<>();
		param.put("userId", userId);
		param.put("likeCondition", year+"%");
		return yueDuKaoHeMapper.selectTotalByUserIdAndYear(param);
	}

	@Override
	public boolean modifyScoreByUserIdAndDate(YueDuKaoHe ydkh) {
		// TODO Auto-generated method stub
		Boolean bRet = true;
		try {
			yueDuKaoHeMapper.updateScoreByUserIdAndDate(ydkh);
		} catch (Exception e) {
			logger.error("保存月度考核数据异常", e);
			bRet = false;
		}
		return bRet;
	}

	@Override
	public boolean setKhjgByUserIdAndDate(YueDuKaoHe ydkh) {
		// TODO Auto-generated method stub
		Boolean bRet = true;
		try {
			yueDuKaoHeMapper.updateKhjgByUserIdAndDate(ydkh);
		} catch (Exception e) {
			logger.error("保存月度考核数据异常", e);
			bRet = false;
		}
		return bRet;
	}

	@Override
	public List<YueDuKaoHe> getByUserIdAndYear(String userId, String year) {
		// TODO Auto-generated method stub
		Map<String, String> param = new HashMap<>();
		param.put("dutyId", userId);
		param.put("likeCondition", year+"%");
		return yueDuKaoHeMapper.selectByUserIdAndYear(param);
	}

	@Override
	public List<YueDuKaoHe> getByDutyIdAndYearMonth(YueDuKaoHe ydkh) {
		// TODO Auto-generated method stub
		return yueDuKaoHeMapper.selectByDutyIdAndYearMonth(ydkh);
	}

}
