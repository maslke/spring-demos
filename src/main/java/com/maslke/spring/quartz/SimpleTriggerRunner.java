package com.maslke.spring.quartz;

import java.util.Date;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author:maslke
 * @date:2/28/2019
 * @version:0.0.1
 */
public class SimpleTriggerRunner {

    public static void  main(String[] args) throws Exception {
        JobDetail jobDetail = new JobDetail("job1", "group1", SimpleJob.class);

        SimpleTrigger trigger = new SimpleTrigger("trigger1", "t_group1");
        trigger.setStartTime(new Date());
        trigger.setRepeatInterval(2000);
        trigger.setRepeatCount(100);

        SchedulerFactory factory = new StdSchedulerFactory();
        Scheduler scheduler = factory.getScheduler();
        scheduler.scheduleJob(jobDetail, trigger);
        scheduler.start();
    }
}
