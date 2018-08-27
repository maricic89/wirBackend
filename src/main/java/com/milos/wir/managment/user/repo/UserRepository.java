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
                            + "  user.CODE "
                            + "FROM "
                            + "  USER user "
                            + "  JOIN USER_ROLE ur on user.ID = ur.USER_ID "
                            + "  JOIN ROLE r on ur.ROLE_ID = r.ID "
                            + "  JOIN ROLE_PERMISSION rp on r.ID = rp.ROLE_ID "
                            + "  JOIN PERMISSION p on rp.PERMISSION_ID = p.ID "
                            + "WHERE "
                            + "  user.id = :id")
    List<String> getActiveAccountPermissionCodesByUserId(@Param("id") Long id);

}
