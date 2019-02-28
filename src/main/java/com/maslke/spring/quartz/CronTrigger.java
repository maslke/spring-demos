package com.maslke.spring.quartz;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author:maslke
 * @date:2/28/2019
 * @version:0.0.1
 */
public class CronTrigger {
    public static void main(String[] args) throws Exception {
        JobDetail jobDetail = new JobDetail("job1", "j_group", SimpleJob.class);

        Trigger trigger = new org.quartz.CronTrigger("trigger1", "t_group");
        ((org.quartz.CronTrigger) trigger).setCronExpression("0/5 * * * * ?");

        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.scheduleJob(jobDetail, trigger);
        scheduler.start();
    }
}
