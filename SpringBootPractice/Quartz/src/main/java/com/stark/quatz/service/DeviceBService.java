package com.stark.quatz.service;

import com.stark.quatz.conn.SerialConn;
import com.stark.quatz.entity.DeviceB;
import com.stark.quatz.schedule.CollectionManage;
import com.stark.quatz.schedule.DeviceBJob;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class DeviceBService {

    private Map<String, DeviceB> deviceMap = new HashMap<>();

    private CollectionManage<DeviceB> collectionManage;

    @Autowired
    private SerialConn conn;

    @Autowired
    private DataService dataService;

    public DeviceBService() {
        deviceMap.put("wind", new DeviceB(1000, "wind_direct"));
    }

    public Collection<DeviceB> getAll() {
        return deviceMap.values();
    }

    public void beginCollect() {
        if (null == collectionManage) {
            collectionManage = new CollectionManage<>();
            collectionManage.setConn(conn);
            collectionManage.setJobClass(DeviceBJob.class);
            collectionManage.setDataService(dataService);
        }
        try {
            collectionManage.addJobs(getAll());
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
