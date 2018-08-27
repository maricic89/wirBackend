package com.milos.wir.security;

public interface TokenBlackListService {

    Boolean isBlackListed(String jti) throws TokenNotFoundException;

    void addToEnabledList(Long userId, String jti, Long expired);

    void addToBlackList(String jti ) throws TokenNotFoundException;

}
