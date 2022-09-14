package com.example.bookstoreorchestrationservice.client;

import com.example.bookstoreorchestrationservice.dto.user.UserValidationRequest;
import com.example.bookstoreorchestrationservice.dto.user.UserValidationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@ConfigurationProperties("app.services.user-service")

public class UserClient {

    private final HttpClient httpClient;
    private static final String USER_URL = "http://localhost:8086/user";

    public ResponseEntity<UserValidationResponse> signIn(UserValidationRequest request) {
        String url = USER_URL + "/signin";
        HttpEntity<UserValidationRequest> requestHttp = new HttpEntity<>(request);
        return httpClient.postForEntity(url, requestHttp, UserValidationResponse.class);
    }

}
