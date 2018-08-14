package org.wangjj.bankperformance.ServiceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wangjj.bankperformance.Entity.Dangliang;
import org.wangjj.bankperformance.Mapper.DangliangMapper;
import org.wangjj.bankperformance.Service.IDangliangService;

@Service
public class DangliangServiceImpl implements IDangliangService {

	private final static Logger logger = LoggerFactory.getLogger(DangliangServiceImpl.class);
	
	@Autowired
	private DangliangMapper dangliangMapper;
	
	@Override
	public boolean modifyValueByCode(Dangliang dl) {
		// TODO Auto-generated method stub
		Boolean bRet = true;
		try {
			dangliangMapper.updateByCode(dl);
		} catch (Exception e) {
			logger.error("保存当量数据异常", e);
			bRet = false;
		}
		return bRet;
	}

	@Override
	public List<Dangliang> getAll() {
		// TODO Auto-generated method stub
		return dangliangMapper.selectAll();
	}

	@Override
	public Dangliang getByCode(String code) {
		// TODO Auto-generated method stub
		return dangliangMapper.selectByCode(code);
	}

	@Override
	public boolean insert(Dangliang dl) {
		// TODO Auto-generated method stub
		Boolean bRet = true;
		try {
			dangliangMapper.insert(dl);
		} catch (Exception e) {
			logger.error("保存当量数据异常", e);
			bRet = false;
		}
		return bRet;
	}


}
