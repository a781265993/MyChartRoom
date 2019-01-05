package com.accp.chatroom.quartz;

import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.accp.chatroom.biz.TblJobBiz;

/**
 * 
 * @ClassName: QuartzRun
 * @Description: 使用Quartz框架的
 * @author 筠颜
 * @date 2018年12月19日 下午2:19:14
 *
 */
public class QuartzRun implements Job{
	@Autowired
	private TblJobBiz biz;
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.err.println(context.getJobDetail().getKey().getName()+"正在执行");
		biz.deleteMessage();
	}

}
