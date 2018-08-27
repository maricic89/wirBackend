package com.milos.wir.managment.user.service;

import com.milos.wir.managment.user.entity.UserEntity;

import java.util.List;

public interface UserService {

    UserEntity findById(Long id);

    UserEntity save(UserEntity userEntity);

    boolean checkIfUserNameExists(String userName);

    List<String> getActiveAccountPermissionCodesByUserId(Long code);

    void findUserByUsername(String username);
}
