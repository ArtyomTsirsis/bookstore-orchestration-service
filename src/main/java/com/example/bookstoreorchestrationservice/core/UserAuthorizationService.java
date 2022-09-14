package com.example.bookstoreorchestrationservice.core;

import com.example.bookstoreorchestrationservice.client.UserClient;
import com.example.bookstoreorchestrationservice.domain.user.UserContext;
import com.example.bookstoreorchestrationservice.domain.user.UserStatus;
import com.example.bookstoreorchestrationservice.dto.user.UserValidationRequest;
import com.example.bookstoreorchestrationservice.dto.user.UserValidationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAuthorizationService {

    private final UserClient client;
    private final UserContext context;

    public void singIn(UserValidationRequest request) {
        ResponseEntity<UserValidationResponse> response = client.signIn(request);
        if (response != null && response.getStatusCode().is2xxSuccessful()) {
            UserValidationResponse responseBody = response.getBody();
            context.authenticate(responseBody);
        }
    }

    public UserValidationResponse getUser() {
        UserValidationResponse user = new UserValidationResponse();
        UserStatus status = context.getStatus();
        if (status.equals(UserStatus.INCORRECT_USER) || status.equals(UserStatus.INCORRECT_PASSWORD)) {
            status = UserStatus.UNAUTHORISED;
        }
        user.setStatus(status);
        user.setLogin(context.getLogin());
        return user;
    }

    public void logOut() {
        context.logOut();
    }

}

