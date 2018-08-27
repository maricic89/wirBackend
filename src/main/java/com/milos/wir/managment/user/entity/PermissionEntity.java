package com.milos.wir.managment.user.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "permission")
public class PermissionEntity implements Serializable {
    private static final long serialVersionUID = 3145480520502998784L;

    @Id
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "code", nullable = false, unique = true, length = 100)
    private String code;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "permission")
    private List<RolePermissionEntity> rolePermissions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<RolePermissionEntity> getRolePermissions() {
        return rolePermissions;
    }

    public void setRolePermissions(List<RolePermissionEntity> rolePermissions) {
        this.rolePermissions = rolePermissions;
    }
}
