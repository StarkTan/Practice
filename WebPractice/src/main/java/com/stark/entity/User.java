package com.stark.entity;

import com.stark.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.List;
import java.util.Set;

@Entity(name = "user")
@Setter
@Getter
public class User extends BaseEntity {

    private String name;

    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;
}
