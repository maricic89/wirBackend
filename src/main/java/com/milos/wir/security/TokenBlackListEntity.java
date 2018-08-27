package com.milos.wir.security;

import javax.persistence.*;

@Entity
@Table(name = "token_black_list")
public class TokenBlackListEntity {

    @Id
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Column(name = "jti")
    private String jti;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "expires")
    private Long expires;

    @Column(name = "is_black_listed")
    private Boolean isBlackListed;

    public TokenBlackListEntity() {
    }

    public TokenBlackListEntity(Long userId, String jti, Long expires) {
        this.jti = jti;
        this.userId = userId;
        this.expires = expires;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJti() {
        return jti;
    }

    public void setJti(String jti) {
        this.jti = jti;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getExpires() {
        return expires;
    }

    public void setExpires(Long expires) {
        this.expires = expires;
    }

    public Boolean getBlackListed() {
        return isBlackListed;
    }

    public void setBlackListed(Boolean blackListed) {
        isBlackListed = blackListed;
    }
}