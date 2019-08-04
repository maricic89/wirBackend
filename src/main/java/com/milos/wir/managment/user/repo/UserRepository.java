package com.milos.wir.managment.user.repo;

import com.milos.wir.managment.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findById(Long id);

    Optional<UserEntity> findByUsername(String username);

    @Query(nativeQuery = true,
            value =
                    "SELECT DISTINCT "
                            + "  usr.* "
                            + "FROM "
                            + "  wir_user usr "
                            + "  JOIN WIR_USER_ROLE ur on usr.ID = ur.USER_ID "
                            + "  JOIN WIR_ROLE r on ur.ROLE_ID = r.ID "
                            + "  JOIN WIR_ROLE_PERMISSION rp on r.ID = rp.ROLE_ID "
                            + "  JOIN WIR_PERMISSION p on rp.PERMISSION_ID = p.ID "
                            + "WHERE "
                            + "  usr.id = :id")
    List<String> getActiveAccountPermissionCodesByUserId(@Param("id") Long id);

}
