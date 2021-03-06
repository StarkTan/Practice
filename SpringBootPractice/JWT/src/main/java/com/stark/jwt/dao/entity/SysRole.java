package com.stark.jwt.dao.entity;

import com.stark.jwt.common.dao.entity.Permission;
import com.stark.jwt.common.dao.entity.Role;

import java.io.Serializable;
import java.util.List;

/**
 * Description  : 角色信息
 */
public class SysRole extends Role implements Serializable {

    private static final long serialVersionUID = 1L;

    // 拥有的权限列表
    private List<Permission> permissions;

    public SysRole() {
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
