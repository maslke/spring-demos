package com.maslke.spring.quartz;

import java.util.Date;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author:maslke
 * @date:2/28/2019
 * @version:0.0.1
 */
public class SimpleJob implements  Job{
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println(" triggered. time is :" + new Date());
    }
}
