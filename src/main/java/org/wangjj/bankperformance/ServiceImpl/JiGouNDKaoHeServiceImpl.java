package org.wangjj.bankperformance.ServiceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wangjj.bankperformance.Entity.JiGouNDKaoHe;
import org.wangjj.bankperformance.Mapper.JiGouNDKaoHeMapper;
import org.wangjj.bankperformance.Service.IJiGouNDKaoHeService;

@Service
public class JiGouNDKaoHeServiceImpl implements IJiGouNDKaoHeService {
	
	private final static Logger logger = LoggerFactory.getLogger(JiGouNDKaoHeServiceImpl.class);

	@Autowired
	JiGouNDKaoHeMapper jiGouNDKaoHeMapper;
	
	@Override
	public boolean insert(JiGouNDKaoHe jgydkh) {
		// TODO Auto-generated method stub
		Boolean bRet = true;
		try {
			jiGouNDKaoHeMapper.insert(jgydkh);
		} catch (Exception e) {
			logger.error("保存机构考核数据异常", e);
			bRet = false;
		}
		return bRet;
	}

	@Override
	public JiGouNDKaoHe getByInsIdAndYear(JiGouNDKaoHe jgydkh) {
		// TODO Auto-generated method stub
		return jiGouNDKaoHeMapper.selectByInsIdAndYear(jgydkh);
	}

	@Override
	public boolean modifyByInsIdAndYear(JiGouNDKaoHe jgydkh) {
		// TODO Auto-generated method stub
		Boolean bRet = true;
		try {
			jiGouNDKaoHeMapper.updateByInsIdAndYear(jgydkh);;
		} catch (Exception e) {
			logger.error("保存机构考核数据异常", e);
			bRet = false;
		}
		return bRet;
	}

	@Override
	public List<JiGouNDKaoHe> getByTypeAndYear(JiGouNDKaoHe jgydkh) {
		// TODO Auto-generated method stub
		return jiGouNDKaoHeMapper.selectByTypeAndYear(jgydkh);
	}

	@Override
	public boolean setKhjgByInsIdAndYear(JiGouNDKaoHe jgydkh) {
		// TODO Auto-generated method stub
		Boolean bRet = true;
		try {
			jiGouNDKaoHeMapper.updateKhjgByInsIdAndYear(jgydkh);
		} catch (Exception e) {
			logger.error("保存机构考核数据异常", e);
			bRet = false;
		}
		return bRet;
	}

	@Override
	public boolean deleteByInsIdAndYear(JiGouNDKaoHe jgndkh) {
		// TODO Auto-generated method stub
		Boolean bRet = true;
		try {
			jiGouNDKaoHeMapper.deleteByInsIdAndYear(jgndkh);
		} catch (Exception e) {
			logger.error("删除机构考核数据失败", e);
			bRet = false;
		}
		return bRet;
	}

	@Override
	public List<String> getYearListByInsId(String insId) {
		// TODO Auto-generated method stub
		return jiGouNDKaoHeMapper.selectGroupByInsIdAndYear(insId);
	}

}
