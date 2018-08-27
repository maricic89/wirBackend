package com.milos.wir.managment.user.mapper;

import com.milos.wir.managment.user.entity.UserRoleEntity;
import com.milos.wir.managment.user.model.UserRole;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserRoleMapper {

    List<UserRole> mapToModelList(List<UserRoleEntity> entityList);

    List<UserRoleEntity> mapToEntityList(List<UserRole> userRoles);

    @Mapping(target = "roleId", source = "role.id")
    @Mapping(target = "roleCode", source = "role.code")
    UserRole mapToModel(UserRoleEntity userRoleEntity);

    @Mapping(target = "role.code", source = "roleCode")
    UserRoleEntity mapToEntity(UserRole userRole);

}
