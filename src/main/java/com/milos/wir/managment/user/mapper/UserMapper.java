package com.milos.wir.managment.user.mapper;

import com.milos.wir.managment.user.entity.UserEntity;
import com.milos.wir.managment.user.model.User;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { UserRoleMapper.class })
@DecoratedWith(UserMapperDecorator.class)
public interface UserMapper {

    @Mapping(target = "password", ignore = true)
    UserEntity mapToAccountEntity(User user);

    @Mapping(target = "password", ignore = true)
    User mapToAccountModel(UserEntity userEntity);

}
