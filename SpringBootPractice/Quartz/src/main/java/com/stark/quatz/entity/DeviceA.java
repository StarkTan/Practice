package com.stark.quatz.entity;

import com.stark.quatz.common.CollectionDevice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class DeviceA extends CollectionDevice {
    private String name;

    public DeviceA(String name) {
        super(1000);
        this.name = name;
    }

    public DeviceA(int rate, String name) {
        super(rate);
        this.name = name;
    }

}
