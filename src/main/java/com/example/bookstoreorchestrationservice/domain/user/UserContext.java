package com.example.bookstoreorchestrationservice.domain.user;

import com.example.bookstoreorchestrationservice.dto.user.UserValidationResponse;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Data
@Component
@SessionScope
public class UserContext {

    private String login;
    private UserStatus status = UserStatus.UNAUTHORISED;

    public void authenticate(UserValidationResponse response) {
        this.login = response.getLogin();
        this.status = response.getStatus();
    }

    public void logOut() {
        this.login = null;
        this.status = UserStatus.UNAUTHORISED;
    }

}
