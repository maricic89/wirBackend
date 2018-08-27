package com.milos.wir.managment.user.facade;

import com.milos.wir.managment.user.model.User;

import java.util.List;


public interface UserFacade {

    User findById(Long id);

    User save(User user);

    User updateUser(User user, String userId);

    boolean checkIfUserNameExists(String userName);

    List<String> getActiveAccountPermissionCodesByUserId(Long id);

    void enableUser(String username);
}
