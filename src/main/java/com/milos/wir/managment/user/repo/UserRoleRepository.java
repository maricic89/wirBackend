package com.milos.wir.managment.user.repo;

import com.milos.wir.managment.user.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {

    UserRoleEntity findByRoleCodeAndUserUsername(String role, String username);

    List<UserRoleEntity> findAllByUserId(Long id);

}
