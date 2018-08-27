package com.milos.wir.managment.user.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "role_permission")
public class RolePermissionEntity  implements Serializable {
    private static final long serialVersionUID = 7497657658507443053L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false, insertable=false, updatable=false)
    private RoleEntity role;

    // bi-directional many-to-one association to Permission
    @ManyToOne
    @JoinColumn(name = "permission_id", referencedColumnName = "id", nullable = false, insertable=false, updatable=false)
    private PermissionEntity permission;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }

    public PermissionEntity getPermission() {
        return permission;
    }

    public void setPermission(PermissionEntity permission) {
        this.permission = permission;
    }
}
