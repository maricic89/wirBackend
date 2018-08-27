package com.milos.wir.configuration;

import com.milos.wir.managment.user.entity.UserEntity;
import com.milos.wir.security.TokenBlackListService;
import com.milos.wir.security.TokenNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;


@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private int accessTokenValiditySeconds = 31556926; // one year
    private int refreshTokenValiditySeconds = 31556926;

    private AuthenticationManager authenticationManager;
    private TokenBlackListService blackListService;

    @Autowired
    public AuthorizationServerConfig(AuthenticationManager authenticationManager, TokenBlackListService blackListService) {
        this.authenticationManager = authenticationManager;
        this.blackListService = blackListService;
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {

        security.tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("AppUser")
                .secret("AppUserPass")
                .scopes("post:read", "post:write")
                .authorizedGrantTypes("password", "refresh_token")
//                .accessTokenValiditySeconds(accessTokenValiditySeconds)
                .refreshTokenValiditySeconds(refreshTokenValiditySeconds);
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("ForMotherRussia1989!@#");
        return converter;
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .authenticationManager(this.authenticationManager)
                .tokenServices(tokenServices())
                .tokenStore(tokenStore())
                .accessTokenConverter(accessTokenConverter());
    }

    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        MyTokenService tokenService = new MyTokenService(blackListService);
        tokenService.setTokenStore(tokenStore());
        tokenService.setSupportRefreshToken(true);
        tokenService.setTokenEnhancer(accessTokenConverter());
        tokenService.setAccessTokenValiditySeconds(accessTokenValiditySeconds);
        return tokenService;
    }

    static class MyTokenService extends DefaultTokenServices {
        Logger logger = LogManager.getLogger(MyTokenService.class);
        private TokenBlackListService blackListService;
        public MyTokenService(TokenBlackListService blackListService) {
            this.blackListService = blackListService;
        }

        @Override
        public OAuth2AccessToken createAccessToken(OAuth2Authentication authentication) throws AuthenticationException {
            OAuth2AccessToken token = super.createAccessToken(authentication);
            UserEntity userEntity = (UserEntity) authentication.getPrincipal();
            String jti = (String) token.getAdditionalInformation().get("jti");

            blackListService.addToEnabledList(
                    userEntity.getId(),
                    jti,
                    token.getExpiration().getTime() );
            return token;
        }

        @Override
        public OAuth2AccessToken refreshAccessToken(String refreshTokenValue, TokenRequest tokenRequest) throws AuthenticationException {
            logger.info("refresh token:" + refreshTokenValue);
            String jti = tokenRequest.getRequestParameters().get("jti");
            try {
                if ( jti != null )
                    if ( blackListService.isBlackListed(jti) ) return null;

                OAuth2AccessToken token = super.refreshAccessToken(refreshTokenValue, tokenRequest);
                blackListService.addToBlackList(jti);
                return token;
            } catch (TokenNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}
