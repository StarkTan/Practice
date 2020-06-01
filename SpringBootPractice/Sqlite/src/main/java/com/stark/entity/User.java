package com.stark.entity;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//表明这是个需要生成数据表的类
@Entity
public class User {
    @Id
    @GenericGenerator(name = "system-uuid", strategy ="uuid")
    @GeneratedValue(generator = "system-uuid")
    private String id;
    private String name;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}