package com.maslke.spring.quartz;

import java.util.Map;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;

/**
 * @author:maslke
 * @date:2/28/2019
 * @version:0.0.1
 */
public class MyJob implements Job {
    public void execute(JobExecutionContext context) throws JobExecutionException {
        Map dataMap = context.getJobDetail().getJobDataMap();
        String size = (String) dataMap.get("size");

        ApplicationContext ctx = (ApplicationContext) dataMap.get("applicationContext");
        System.out.println("size:" + size);
        ((JobDataMap) dataMap).put("size", size + "0");

    }
}
