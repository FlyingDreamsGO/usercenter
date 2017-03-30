package com.beauty.usercenter.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**  
 * @author SuoLong
 */
public class ContextStartUpListener implements ApplicationListener<ContextRefreshedEvent>{
	private static final Logger logger = LoggerFactory.getLogger(ContextStartUpListener.class);
	private boolean isStarted = false;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		Task task = new Task();
		task.start();
	}
	
	private class Task extends Thread  {

		public void run() {
			if( isStarted ){
				return ;
			}
			isStarted = true ;
		}

	}


}
