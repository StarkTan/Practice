package com.stark.quatz.common;

import java.io.Serializable;
import java.util.UUID;

public class BaseEntity implements Serializable {

    private String id;

    public BaseEntity() {
        this.id = UUID.randomUUID().toString().replace("-", "");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (null == this.id) {
            this.id = id;
        }
    }
}
