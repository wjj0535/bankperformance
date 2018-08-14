package org.wangjj.bankperformance.ServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wangjj.bankperformance.Entity.JiangJinChi;
import org.wangjj.bankperformance.Mapper.JiangJinChiMapper;
import org.wangjj.bankperformance.Service.IJiangJinChiService;

@Service
public class JiangJinChiServiceImpl implements IJiangJinChiService {

	private final static Logger logger = LoggerFactory.getLogger(GaoGuanYDKaoHeServiceImpl.class);
	
	@Autowired
	JiangJinChiMapper jiangJinChiMapper;
	
	@Override
	public boolean insert(JiangJinChi jjc) {
		// TODO Auto-generated method stub
		Boolean bRet = true;
		try {
			jiangJinChiMapper.insert(jjc);
		} catch (Exception e) {
			logger.error("保存奖金池数据异常", e);
			bRet = false;
		}
		return bRet;
	}

	@Override
	public List<JiangJinChi> getByYear(String year) {
		// TODO Auto-generated method stub
		return jiangJinChiMapper.selectByYear(year);
	}

	@Override
	public boolean modifyByYearAndDutyId(JiangJinChi jjc) {
		// TODO Auto-generated method stub
		Boolean bRet = true;
		try {
			jiangJinChiMapper.updateByDutyIdAndYear(jjc);
		} catch (Exception e) {
			logger.error("保存奖金池数据异常", e);
			bRet = false;
		}
		return bRet;
	}

	@Override
	public Set<String> getAllYears() {
		// TODO Auto-generated method stub
		return jiangJinChiMapper.selectAllYear();
	}

	@Override
	public boolean removeByYearAndDutyId(JiangJinChi jjc) {
		// TODO Auto-generated method stub
		Boolean bRet = true;
		try {
			jiangJinChiMapper.deleteByDutiIdAndYear(jjc);
		} catch (Exception e) {
			logger.error("保存奖金池数据异常", e);
			bRet = false;
		}
		return bRet;
	}

	@Override
	public JiangJinChi getByDutyIdAndYear(String dutyId, String year) {
		// TODO Auto-generated method stub
		Map<String, String> param = new HashMap<>();
		param.put("likeCondition", "%"+dutyId+",%");
		param.put("year", year);
		return jiangJinChiMapper.selectByDutyIdAndYear(param);
	}

}
