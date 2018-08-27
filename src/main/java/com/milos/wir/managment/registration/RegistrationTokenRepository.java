package com.milos.wir.managment.registration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegistrationTokenRepository extends JpaRepository<RegistrationTokenEntity, Long> {

    Optional<RegistrationTokenEntity> findByEmail(String userEmail);

    Optional<RegistrationTokenEntity> findByRegistrationToken(String token);

}