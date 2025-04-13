package com.auth.oauth2client.controller;

import com.auth.oauth2client.service.OAuth2TokenClient;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthCallbackController {

    private final OAuth2TokenClient oAuth2TokenClient;
    private static final String jsessionid = "AUTH_SESSION_USER";

    // 콜백 URI로, OAuth2 에서 받은 인가 코드가 전달되는 곳
    // 이곳에서 인가 코드를 사용하여 액세스 토큰을 요청하고, 사용자 정보를 가져온다.
    @PostMapping("/auth/callback")
    public String handleAuthCallback(@RequestBody AuthCallbackRequest authCallbackRequest, HttpSession session) {
        String authenticationCode = authCallbackRequest.code;

        String accessToken = oAuth2TokenClient.getAccessToken(authenticationCode);
        Object userInfo = oAuth2TokenClient.fetchUserInfo(accessToken);

        session.setAttribute(jsessionid, new SessionValue(accessToken, userInfo));
        return "login success";
    }


    public record AuthCallbackRequest(
            String code,
            String state
    ) {
    }

    public record SessionValue(
            String accessToken,
            Object userInfo
    ) {
    }

}
