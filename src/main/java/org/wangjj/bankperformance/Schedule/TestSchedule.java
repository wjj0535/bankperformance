package org.wangjj.bankperformance.Schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.wangjj.bankperformance.Entity.YueDuKaoHe;
import org.wangjj.bankperformance.Service.IYueDuKaoHeService;

@Service
public class TestSchedule {
	
	@Autowired
	IYueDuKaoHeService yueDuKaoHeService;
	
	//@Scheduled(cron="*/1 * * * * ?")
	public void t1()
	{
//		long start = System.currentTimeMillis();
//		for(int i=0 ; i<100000; i++)
//		{
//			YueDuKaoHe ydkh = new YueDuKaoHe();
//			ydkh.setZongLiang(10);
//			yueDuKaoHeService.insert(ydkh);
//		}
//		long end = System.currentTimeMillis();
//		System.out.println(String.format("总耗时", end - start));
	}

}
