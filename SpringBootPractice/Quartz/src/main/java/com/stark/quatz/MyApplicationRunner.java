package com.stark.quatz;

import com.stark.quatz.service.DeviceAService;
import com.stark.quatz.service.DeviceBService;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

@Component
public class MyApplicationRunner implements ApplicationRunner {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DeviceAService serviceA;

    @Autowired
    private DeviceBService serviceB;

    @Override
    public void run(ApplicationArguments var1) throws SchedulerException {

        logger.info("server is stared! start the modbus server");
        serviceA.beginCollect();
        serviceB.beginCollect();
    }
}