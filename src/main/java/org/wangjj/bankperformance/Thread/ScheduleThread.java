package org.wangjj.bankperformance.Thread;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ScheduledFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

public class ScheduleThread {
	
	private static Logger logger = LoggerFactory.getLogger(ScheduleThread.class);
	
	private static String[] szDayOfWeek = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
	
    private static ThreadPoolTaskScheduler threadPoolTaskScheduler ;
	private static ConcurrentMap<String, ScheduledFuture<?>> threadMap;
	static {
		threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
		threadPoolTaskScheduler.initialize();
		threadMap = new ConcurrentHashMap<>();
	}
	
	public static void addSubscribeThread(String userId)
	{
//		//有可能重复，先停掉原来的，再启动
//		cancelSubscribeThread(userId);
//		//需校验time
//		StringBuilder sb = new StringBuilder();
//		if(!Pattern.matches("\\d{2}:\\d{2}", subscribe.getTime()))
//		{
//			logger.error("添加邮件订阅线程时，时间格式错误");
//			return;
//		}
//		String[] szTime = subscribe.getTime().split(":");
//		sb.append("0"); //秒
//		sb.append(" ");
//		sb.append(szTime[1]);//分
//		sb.append(" ");
//		sb.append(szTime[0]);//时
//		sb.append(" ");
//		sb.append("?"); //日期
//		sb.append(" ");
//		sb.append("*"); //月份
//		sb.append(" ");
//		//周几
//		if(subscribe.getFrequency().equals(SUBSCRIBE_FREQUNCY.DAY.getStr()))
//			sb.append("*"); 
//		if(subscribe.getFrequency().equals(SUBSCRIBE_FREQUNCY.WEEK.getStr()))
//			sb.append(szDayOfWeek[Integer.valueOf(subscribe.getDayOfWeek()) - 1]);
//		ScheduledFuture<?> future = threadPoolTaskScheduler.schedule(new SubscribeThread(userId), new CronTrigger(sb.toString()));
//		threadMap.put(userId, future);
	}
	
	public static void cancelSubscribeThread(String userId)
	{
		Iterator<Map.Entry<String, ScheduledFuture<?>>> iter = threadMap.entrySet().iterator();
		while(iter.hasNext())
		{
			Map.Entry<String, ScheduledFuture<?>> item = iter.next();
			if(userId.equals(item.getKey()))
			{
				item.getValue().cancel(true);
			}
		}
	}
	
	public static void main(String[] args) {
//		ScheduledFuture<?> future = threadPoolTaskScheduler.schedule(new SubscribeThread("1"), new CronTrigger("35 21 12 * * 4"));
//		ScheduleThread.addSubscribeThread("1", null);
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		ScheduleThread.cancelSubscribeThread("1");
	}
}
