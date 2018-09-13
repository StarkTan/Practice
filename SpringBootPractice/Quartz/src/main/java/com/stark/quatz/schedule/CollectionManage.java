package com.stark.quatz.schedule;

import com.stark.quatz.common.CollectionDevice;
import lombok.Getter;
import lombok.Setter;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Created by Stark on 2017/11/21.
 */
@Getter
@Setter
public class CollectionManage<T extends CollectionDevice> {

    private Scheduler sched;

    private Class jobClass;

    private Object conn;

    private Object dataService;

    public CollectionManage() {
        SchedulerFactory sf = new StdSchedulerFactory();
        try {
            this.sched = sf.getScheduler();
            sched.start();
        } catch (SchedulerException e) {
            this.sched = null;
        }
    }

    public void addJobs(Collection<T> devices) throws SchedulerException {
        List<String> jobs = sched.getTriggerGroupNames();
        List<String> deviceIds = new ArrayList<>();
        for (T device : devices) {
            deviceIds.add(device.getId());
        }
        List<JobKey> nextStop = new ArrayList<>();
        List<T> nextStart = new ArrayList<>();

        for (String job : jobs) {
            if (!deviceIds.contains(job)) nextStop.add(new JobKey(job, jobClass.getName()));
        }
        for (T device : devices) {
            if (!jobs.contains(device.getId())) nextStart.add(device);
        }
        sched.deleteJobs(nextStop);

        JobDetail job;
        SimpleTrigger trigger;

        for (T device : nextStart) {
            String id = device.getId();
            job = newJob(jobClass)
                    .withIdentity(new JobKey(id, jobClass.getName()))
                    .build();
            trigger = newTrigger()
                    .withIdentity(new TriggerKey(id, jobClass.getName()))
                    .startNow()
                    .withSchedule(simpleSchedule()
                            .withIntervalInMilliseconds(device.getRate())
                            .repeatForever())
                    .build();
            job.getJobDataMap().put("conn", conn);
            job.getJobDataMap().put("dataService", dataService);
            job.getJobDataMap().put("device", device);
            sched.scheduleJob(job, trigger);
        }
    }

    public void changeRate(String id, int rate) throws SchedulerException {
        JobDetail jobDetail = sched.getJobDetail(new JobKey(id, jobClass.getName()));
        if (jobDetail == null) return;
        SimpleTrigger trigger = newTrigger()
                .withIdentity(new TriggerKey(id, jobClass.getName()))
                .startNow()
                .withSchedule(simpleSchedule()
                        .withIntervalInMilliseconds(rate)
                        .repeatForever())
                .build();
        sched.rescheduleJob(new TriggerKey(id, jobClass.getName()), trigger);
    }
}
