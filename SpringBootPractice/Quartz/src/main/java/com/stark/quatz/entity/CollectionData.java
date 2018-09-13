package com.stark.quatz.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CollectionData {

    private String deviceId;

    private int data;

    @Override
    public String toString() {
        return deviceId + " , " + data;
    }
}
