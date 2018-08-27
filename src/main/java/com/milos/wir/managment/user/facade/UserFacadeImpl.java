package com.milos.wir.managment.user.facade;

import com.milos.wir.managment.user.entity.UserEntity;
import com.milos.wir.managment.user.mapper.UserMapper;
import com.milos.wir.managment.user.model.User;
import com.milos.wir.managment.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class UserFacadeImpl implements UserFacade {

    private UserService userService;

    private UserMapper userMapper;

    @Autowired
    public UserFacadeImpl(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @Override
    public User findById(Long accountId) {
        UserEntity userEntity = userService.findById(accountId);
        return userMapper.mapToAccountModel(userEntity);
    }

    @Override
    public User save(User user) {
        if(!userService.checkIfUserNameExists(user.getUsername())){
            //TODO implement exception
        }
        UserEntity mappedUserEntity = userMapper.mapToAccountEntity(user);
        UserEntity savedUserEntity = userService.save(mappedUserEntity);
        return userMapper.mapToAccountModel(savedUserEntity);
    }

    @Override
    public User updateUser(User user, String userId) {
        return null;
    }

    public boolean checkIfUserNameExists(String userName){
        return userService.checkIfUserNameExists(userName);
    }

    @Override
    public List<String> getActiveAccountPermissionCodesByUserId(Long id) {
        return userService.getActiveAccountPermissionCodesByUserId(id);
    }

    @Override
    public void enableUser(String username) {
        userService.findUserByUsername(username);
    }
}
