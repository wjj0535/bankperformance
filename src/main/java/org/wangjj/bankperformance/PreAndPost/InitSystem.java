package org.wangjj.bankperformance.PreAndPost;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.wangjj.bankperformance.Thread.WorkThread;

@Component
public class InitSystem implements CommandLineRunner {

	@Override
	public void run(String... arg0) throws Exception {
		// TODO Auto-generated method stub
		WorkThread.start();
	}

}
