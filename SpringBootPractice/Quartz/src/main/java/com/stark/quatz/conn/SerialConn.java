package com.stark.quatz.conn;

import com.stark.quatz.entity.DeviceA;
import com.stark.quatz.entity.DeviceB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class SerialConn {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public int getData(DeviceA device) {
        logger.info("device: " + device.getName() + " get data!");
        return new Random().nextInt(1000);
    }

    public int getData(DeviceB device) {
        logger.info("device: " + device.getName() + " get data!");
        return new Random().nextInt(1000);
    }

}
