package org.wangjj.bankperformance.Thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WorkThread implements Runnable{
	
	private static final Logger logger = LoggerFactory.getLogger(WorkThread.class);

	private static BlockingQueue<Runnable> work_queue = new ArrayBlockingQueue<>(1000);
	
	private static Thread thread ;
	
	public static void putWork(Runnable work)
	{
		try {
			work_queue.put(work);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Runnable work = null;
		while(!Thread.currentThread().isInterrupted())
		{
			try {
				work = work_queue.take();
				work.run();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch(Exception e)
			{
				logger.error("工作线程异常", e);
			}
		}
		System.out.println("后台工作线程终止");
	}
	
	public static void start()
	{
		WorkThread wt = new WorkThread();
		thread = new Thread(wt);
		thread.start();
		System.out.println("启动后台工作线程");
	}
	
	public static void stop()
	{
		thread.interrupt();
		System.out.println("结束后台工作线程...");
	}
}
