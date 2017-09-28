package com.zw.admin.server.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.zw.admin.server.config.JobConfig;
import com.zw.admin.server.service.SysLogService;

public class DeleteLogJob extends QuartzJobBean {

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		try {
			ApplicationContext applicationContext = (ApplicationContext) context.getScheduler().getContext()
					.get(JobConfig.KEY);
			SysLogService service = applicationContext.getBean(SysLogService.class);
			service.deleteLogs();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}

	}

}
