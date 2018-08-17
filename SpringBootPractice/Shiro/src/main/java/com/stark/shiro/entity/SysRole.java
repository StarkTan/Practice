package com.stark.shiro.entity;


import com.stark.shiro.common.entity.Permission;
import com.stark.shiro.common.entity.Role;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * Description  : 角色信息
 */
@Getter
@Setter
public class SysRole extends Role implements Serializable {

    private static final long serialVersionUID = 1L;

    // 拥有的权限列表
    private List<Permission> permissions;

}
