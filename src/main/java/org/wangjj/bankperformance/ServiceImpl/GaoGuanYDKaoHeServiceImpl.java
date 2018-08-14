package org.wangjj.bankperformance.ServiceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wangjj.bankperformance.Entity.GaoGuanYDKaoHe;
import org.wangjj.bankperformance.Entity.JiGouYDKaoHe;
import org.wangjj.bankperformance.Mapper.GaoGuanYDKaoHeMapper;
import org.wangjj.bankperformance.Mapper.JiGouYDKaoHeMapper;
import org.wangjj.bankperformance.Service.IGaoGuanYDKaoHeService;
import org.wangjj.bankperformance.Service.IJiGouYDKaoHeService;

@Service
public class GaoGuanYDKaoHeServiceImpl implements IGaoGuanYDKaoHeService {
	
	private final static Logger logger = LoggerFactory.getLogger(GaoGuanYDKaoHeServiceImpl.class);

	@Autowired
	GaoGuanYDKaoHeMapper gaoGuanYDKaoHeMapper;
	
	@Override
	public boolean insert(GaoGuanYDKaoHe ggydkh) {
		// TODO Auto-generated method stub
		Boolean bRet = true;
		try {
			gaoGuanYDKaoHeMapper.insert(ggydkh);
		} catch (Exception e) {
			logger.error("保存年度考核数据异常", e);
			bRet = false;
		}
		return bRet;
	}

	@Override
	public List<GaoGuanYDKaoHe> getByDutyAndYearMonth(GaoGuanYDKaoHe ggydkh) {
		// TODO Auto-generated method stub
		return gaoGuanYDKaoHeMapper.selectByDutyAndYearMonth(ggydkh);
	}

	@Override
	public boolean modifyByInsIdAndYearMonth(GaoGuanYDKaoHe ggydkh) {
		// TODO Auto-generated method stub
		Boolean bRet = true;
		try {
			gaoGuanYDKaoHeMapper.updateByUserIdAndYearMonth(ggydkh);
		} catch (Exception e) {
			logger.error("保存年度考核数据异常", e);
			bRet = false;
		}
		return bRet;
	}



}
