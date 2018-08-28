package com.stark.entity;


import com.stark.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Setter
@Getter
public class Resource extends BaseEntity {

    private String name;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "resources")
    private List<Role> roles;
}
