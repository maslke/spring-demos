package com.maslke.spring.quartz;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobDetail;
import org.quartz.ScheduleBuilder;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.CronTriggerImpl;

/**
 * @author:maslke
 * @date:2/28/2019
 * @version:0.0.1
 */
public class CronTrigger {
    public static void main(String[] args) throws Exception {
        JobDetail jobDetail = new JobDetailImpl("job1", "j_group", SimpleJob.class);

        //TriggerBuilder.newTrigger().withIdentity("trigger1", "t_group").withSchedule(CronScheduleBuilder
        // .cronSchedule("0/5 * * * * *")).build();
        CronTriggerImpl trigger = new CronTriggerImpl("trigger1", "t_group");
        trigger.setCronExpression("0/5 * * * * ?");

        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.scheduleJob(jobDetail, trigger);
        scheduler.start();
    }
}
