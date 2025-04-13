package com.auth.oauth2client.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class LoginController {

    @GetMapping("/login/oauth2")
    public ResponseEntity<Map<String, Object>> getResource() {
        Map<String, Object> response = new HashMap<>();
        response.put("authorization", new AuthResponse());
        return ResponseEntity.ok(response);
    }

    @Data
    public static class AuthResponse {
        private String clientId = "my-service-id";
        private String redirectUrl = "https://accounts.google.com/o/oauth2/v2/auth";
        private String responseType = "code";
        private String scope = "read write";
        private String state = UUID.randomUUID().toString();
    }
}
