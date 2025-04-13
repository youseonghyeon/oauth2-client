package com.auth.oauth2client.service;

import org.springframework.stereotype.Service;

@Service
public class OAuth2TokenClient {


    public String getAccessToken(String authenticationCode) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object fetchUserInfo(String accessToken) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
