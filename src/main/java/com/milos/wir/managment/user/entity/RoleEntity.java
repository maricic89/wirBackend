package com.milos.wir.managment.user.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "role")
public class RoleEntity implements Serializable {
    private static final long serialVersionUID = -4060817877772241700L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "role", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
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
