package org.wangjj.bankperformance.ServiceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wangjj.bankperformance.Entity.JiGouYDKaoHe;
import org.wangjj.bankperformance.Mapper.JiGouYDKaoHeMapper;
import org.wangjj.bankperformance.Service.IJiGouYDKaoHeService;

@Service
public class JiGouYDKaoHeServiceImpl implements IJiGouYDKaoHeService {
	
	private final static Logger logger = LoggerFactory.getLogger(JiGouYDKaoHeServiceImpl.class);

	@Autowired
	JiGouYDKaoHeMapper jiGouYDKaoHeMapper;
	
	@Override
	public boolean insert(JiGouYDKaoHe jgydkh) {
		// TODO Auto-generated method stub
		Boolean bRet = true;
		try {
			jiGouYDKaoHeMapper.insert(jgydkh);
		} catch (Exception e) {
			logger.error("保存机构考核数据异常", e);
			bRet = false;
		}
		return bRet;
	}

	@Override
	public JiGouYDKaoHe getByInsIdAndYearMonth(JiGouYDKaoHe jgydkh) {
		// TODO Auto-generated method stub
		return jiGouYDKaoHeMapper.selectByInsIdAndYearMonth(jgydkh);
	}

	@Override
	public boolean modifyByInsIdAndYearMonth(JiGouYDKaoHe jgydkh) {
		// TODO Auto-generated method stub
		Boolean bRet = true;
		try {
			jiGouYDKaoHeMapper.updateByInsIdAndYearMonth(jgydkh);;
		} catch (Exception e) {
			logger.error("保存机构考核数据异常", e);
			bRet = false;
		}
		return bRet;
	}

	@Override
	public List<JiGouYDKaoHe> getByTypeAndYearMonth(JiGouYDKaoHe jgydkh) {
		// TODO Auto-generated method stub
		return jiGouYDKaoHeMapper.selectByTypeAndYearMonth(jgydkh);
	}

	@Override
	public boolean setKhjgByInsIdAndYearMonth(JiGouYDKaoHe jgydkh) {
		// TODO Auto-generated method stub
		Boolean bRet = true;
		try {
			jiGouYDKaoHeMapper.updateKhjgByInsIdAndYearMonth(jgydkh);
		} catch (Exception e) {
			logger.error("保存机构考核数据异常", e);
			bRet = false;
		}
		return bRet;
	}

	@Override
	public List<JiGouYDKaoHe> getByInsIdAndYear(JiGouYDKaoHe jgydkh) {
		// TODO Auto-generated method stub
		return jiGouYDKaoHeMapper.selectByInsIdAndYear(jgydkh);
	}


}
