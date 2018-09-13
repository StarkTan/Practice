package com.stark.quatz.service;

import com.stark.quatz.conn.SerialConn;
import com.stark.quatz.entity.DeviceA;
import com.stark.quatz.schedule.CollectionManage;
import com.stark.quatz.schedule.DeviceAJob;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class DeviceAService {

    private Map<String, DeviceA> deviceMap = new HashMap<>();

    private CollectionManage<DeviceA> collectionManage;

    @Autowired
    private SerialConn conn;

    @Autowired
    private DataService dataService;

    public DeviceAService() {
        deviceMap.put("wind", new DeviceA(1000, "wind"));
        deviceMap.put("temperature", new DeviceA(2000, "temperature"));
    }

    public Collection<DeviceA> getAll() {
        return deviceMap.values();
    }

    public void beginCollect() {
        if (null == collectionManage) {
            collectionManage = new CollectionManage<>();
            collectionManage.setConn(conn);
            collectionManage.setJobClass(DeviceAJob.class);
            collectionManage.setDataService(dataService);
        }
        try {
            collectionManage.addJobs(getAll());
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
