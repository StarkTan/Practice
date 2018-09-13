package com.stark.quatz.schedule;

import com.stark.quatz.conn.SerialConn;
import com.stark.quatz.entity.CollectionData;
import com.stark.quatz.entity.DeviceA;
import com.stark.quatz.service.DataService;
import com.stark.quatz.service.DeviceAService;
import org.quartz.*;

/**
 * Created by Stark on 2017/11/21.
 */
public class DeviceAJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap data = context.getJobDetail().getJobDataMap();

        DataService dataService = (DataService) data.get("dataService");
        SerialConn conn = (SerialConn) data.get("conn");
        DeviceA device = (DeviceA) data.get("device");
        int data1 = conn.getData(device);
        dataService.save(new CollectionData(device.getId(), data1));
    }
}
