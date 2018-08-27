package com.milos.wir.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TokenBlackListServiceImpl implements TokenBlackListService {

    private TokenBlackListRepo tokenBlackListRepo;

    @Autowired
    public TokenBlackListServiceImpl(TokenBlackListRepo tokenBlackListRepo) {
        this.tokenBlackListRepo = tokenBlackListRepo;
    }

    @Override
    public Boolean isBlackListed(String jti ) throws TokenNotFoundException {
        Optional<TokenBlackListEntity> token = tokenBlackListRepo.findByJti(jti);
        if ( token.isPresent() ) {
            return token.get().getBlackListed();
        } else {
            throw new TokenNotFoundException("Token with" + jti + " not found.");
        }
    }

    @Override
    @Async
    public void addToEnabledList(Long userId, String jti, Long expired ) {
        // clean all black listed tokens for user
        List<TokenBlackListEntity> list = tokenBlackListRepo.queryAllByUserIdAndIsBlackListedTrue(userId);
        if (list != null && list.size() > 0) {
            list.forEach(
                    token -> {
                        token.setBlackListed(true);
                        tokenBlackListRepo.save(token);
                    }
            );
        }
        // Add new token white listed
        TokenBlackListEntity tokenBlackList = new TokenBlackListEntity(userId, jti, expired);
        tokenBlackList.setBlackListed(false);
        tokenBlackListRepo.save(tokenBlackList);
        tokenBlackListRepo.deleteAllByUserIdAndExpiresBefore(userId, new Date().getTime());
    }

    @Override
    @Async
    public void addToBlackList(String jti ) throws TokenNotFoundException {
        Optional<TokenBlackListEntity> tokenBlackList = tokenBlackListRepo.findByJti(jti);
        if ( tokenBlackList.isPresent() ) {
            tokenBlackList.get().setBlackListed(true);
            tokenBlackListRepo.save(tokenBlackList.get());
        } else throw new TokenNotFoundException("Token with" + jti + " not found.");
    }

}
