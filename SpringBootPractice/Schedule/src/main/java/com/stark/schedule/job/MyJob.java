package com.stark.schedule.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 定时任务
 * <p>
 * 第一位，表示秒，取值0-59
 * 第二位，表示分，取值0-59
 * 第三位，表示小时，取值0-23
 * 第四位，日期天/日，取值1-31
 * 第五位，日期月份，取值1-12
 * 第六位，星期，取值1-7，1表示星期天，2表示星期一
 * 第七位，年份，可以留空，取值1970-2099
 */
@Component
public class MyJob {
    private static final Logger logger = LoggerFactory.getLogger(MyJob.class);

    @Scheduled(fixedDelay = 60000)
    public void fixedDelayJob() throws InterruptedException {
        logger.info(new Date() + " >>fixedDelay执行.... start");
        Thread.sleep(5000L);
    }

    @Scheduled(fixedRate = 60000)
    public void fixedRateJob() throws InterruptedException {
        logger.info(new Date() + " >>fixedRate执行....");
        Thread.sleep(8000L);
    }

    /**
     * 第一位，表示秒，取值0-59
     * 第二位，表示分，取值0-59
     * 第三位，表示小时，取值0-23
     * 第四位，日期天/日，取值1-31
     * 第五位，日期月份，取值1-12
     * 第六位，星期，取值1-7，1表示星期天，2表示星期一
     * 第七位，年份，可以留空，取值1970-2099
     */
    @Scheduled(cron = "0 40 19 * * ?")
    public void cronJob() {
        logger.info(new Date() + " >>cron执行....");
    }


}