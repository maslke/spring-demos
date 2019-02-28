package com.maslke.spring.quartz;

import java.util.Date;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.core.jmx.JobDetailSupport;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.SimpleTriggerImpl;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;

/**
 * @author:maslke
 * @date:2/28/2019
 * @version:0.0.1
 */
public class SimpleTriggerRunner {

    public static void  main(String[] args) throws Exception {
        //JobKey jobKey = new JobKey("job1", "group1");
        //JobDetail jobDetail = JobBuilder.newJob(SimpleJob.class).withIdentity("job1", "group1").build();
        JobDetail jobDetail = new JobDetailImpl("job1", "group1", SimpleJob.class);

        SimpleTriggerImpl trigger = new SimpleTriggerImpl("trigger1", "t_group1");
        trigger.setStartTime(new Date());
        trigger.setRepeatInterval(2000);
        trigger.setRepeatCount(100);

        SchedulerFactory factory = new StdSchedulerFactory();
        Scheduler scheduler = factory.getScheduler();
        scheduler.scheduleJob(jobDetail, trigger);
        scheduler.start();
    }
}
