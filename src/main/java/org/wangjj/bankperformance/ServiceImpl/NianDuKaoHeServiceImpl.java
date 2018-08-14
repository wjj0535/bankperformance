package org.wangjj.bankperformance.ServiceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wangjj.bankperformance.Entity.NianDuKaoHe;
import org.wangjj.bankperformance.Mapper.NianDuKaoHeMapper;
import org.wangjj.bankperformance.Service.INianDuKaoHeService;

@Service
public class NianDuKaoHeServiceImpl implements INianDuKaoHeService {

	private final static Logger logger = LoggerFactory.getLogger(YueDuKaoHeServiceImpl.class);
	
	@Autowired
	NianDuKaoHeMapper nianDuKaoHeMapper;
	
	@Override
	public boolean insert(NianDuKaoHe ndkh) {
		// TODO Auto-generated method stub
		Boolean bRet = true;
		try {
			nianDuKaoHeMapper.insert(ndkh);
		} catch (Exception e) {
			logger.error("保存年度考核数据异常", e);
			bRet = false;
		}
		return bRet;
	}

	@Override
	public NianDuKaoHe getByUserIdAndYear(NianDuKaoHe ndkh) {
		// TODO Auto-generated method stub
		return nianDuKaoHeMapper.selectByUserIdAndYear(ndkh);
	}

	@Override
	public boolean modifyByUserIdAndYear(NianDuKaoHe ndkh) {
		// TODO Auto-generated method stub
		Boolean bRet = true;
		try {
			nianDuKaoHeMapper.updateByUserIdAndYear(ndkh);
		} catch (Exception e) {
			logger.error("保存年度考核数据异常", e);
			bRet = false;
		}
		return bRet;
	}

	@Override
	public List<NianDuKaoHe> getCurInsDutyYearUsers(NianDuKaoHe ndkh) {
		// TODO Auto-generated method stub
		return nianDuKaoHeMapper.selectByYearAndInsAndDuty(ndkh);
	}

	@Override
	public List<NianDuKaoHe> getByYearAndXulieId(NianDuKaoHe ndkh) {
		// TODO Auto-generated method stub
		return nianDuKaoHeMapper.selectByYearAndXulieId(ndkh);
	}

	@Override
	public boolean modifyScoreByUserIdAndYear(NianDuKaoHe ndkh) {
		// TODO Auto-generated method stub
		Boolean bRet = true;
		try {
			nianDuKaoHeMapper.updateScoreByUserIdAndYear(ndkh);
		} catch (Exception e) {
			logger.error("保存年度考核分数异常", e);
			bRet = false;
		}
		return bRet;
	}

	@Override
	public boolean setKhjgByUserIdAndDate(NianDuKaoHe ndkh) {
		// TODO Auto-generated method stub
		Boolean bRet = true;
		try {
			nianDuKaoHeMapper.updateKhjgByUserIdAndYear(ndkh);
		} catch (Exception e) {
			logger.error("年度考核排名异常", e);
			bRet = false;
		}
		return bRet;
	}

	@Override
	public List<NianDuKaoHe> getByYearAndDutyId(NianDuKaoHe ndkh) {
		// TODO Auto-generated method stub
		return nianDuKaoHeMapper.selectByYearAndDuty(ndkh);
	}

}
