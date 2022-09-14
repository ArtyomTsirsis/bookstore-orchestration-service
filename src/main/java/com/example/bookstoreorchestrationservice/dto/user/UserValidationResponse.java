package com.example.bookstoreorchestrationservice.dto.user;

import com.example.bookstoreorchestrationservice.domain.user.UserStatus;
import lombok.Data;

@Data
public class UserValidationResponse {

    private String login;
    private UserStatus status;

}
