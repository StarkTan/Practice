package com.stark.quatz.entity;

import com.stark.quatz.common.CollectionDevice;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DeviceB extends CollectionDevice {
    private String name;

    public DeviceB(String name) {
        super(1000);
        this.name = name;
    }

    public DeviceB(int rate, String name) {
        super(rate);
        this.name = name;
    }

}
