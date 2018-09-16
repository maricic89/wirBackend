package com.milos.wir.managment.user.mapper;

import com.milos.wir.managment.user.entity.UserEntity;
import com.milos.wir.managment.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;


public abstract class UserMapperDecorator implements UserMapper {

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserMapper delegate;

    public UserEntity mapToAccountEntity(User user){
        UserEntity entity = new UserEntity();
        entity = delegate.mapToAccountEntity(user);
        entity.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        return entity;
    }

}
