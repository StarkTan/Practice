package com.stark.entity;

import com.stark.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Role extends BaseEntity {
    private String name;

    private String description;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Resource> resources;

    @ManyToMany( mappedBy = "roles")
    private List<User> users;
}
