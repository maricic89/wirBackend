package com.milos.wir.managment.user.model;

import java.io.Serializable;

public class UserRole implements Serializable {
    private static final long serialVersionUID = 5841121861285838537L;

    private Long roleId;

    private String roleCode;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }
}
