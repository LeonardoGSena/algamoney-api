package com.sena.leonardo.algamoneyapi.config.token;

import com.sena.leonardo.algamoneyapi.security.UserApp;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

public class CustomTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        UserApp userApp = (UserApp) oAuth2Authentication.getPrincipal();

        Map<String, Object> addInfo = new HashMap<>();
        addInfo.put("name", userApp.getUserSystem().getName());

        ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(addInfo);
        return oAuth2AccessToken;
    }
}
