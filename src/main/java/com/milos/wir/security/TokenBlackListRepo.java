package com.milos.wir.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenBlackListRepo extends JpaRepository<TokenBlackListEntity, Long> {

    Optional<TokenBlackListEntity> findByJti(String jti);

    List<TokenBlackListEntity> queryAllByUserIdAndIsBlackListedTrue(Long userId);

    List<TokenBlackListEntity> deleteAllByUserIdAndExpiresBefore(Long userId, Long date);
}
