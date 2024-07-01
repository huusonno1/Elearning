package com.codewithson.Elearning.controllers;

import com.codewithson.Elearning.dtos.UserDTO;
import com.codewithson.Elearning.dtos.UserLoginDTO;
import com.codewithson.Elearning.models.User;
import com.codewithson.Elearning.response.ResponseObject;
import com.codewithson.Elearning.response.user.AuthenticationResponse;
import com.codewithson.Elearning.response.user.UserResponse;
import com.codewithson.Elearning.services.user.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    @PostMapping("/register")
    public ResponseEntity<ResponseObject> createUser(
            @Valid @RequestBody UserDTO userDTO,
            BindingResult result
    )throws Exception{
        ResponseEntity<ResponseObject> validationResponse = handleValidationErrors(result);

        if(userDTO.getUsername() == null || userDTO.getUsername().trim().isBlank()) {
            return ResponseEntity.badRequest().body(ResponseObject.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .data(null)
                    .message("username is required")
                    .build());
        }

        if(!userDTO.getPassword().equals(userDTO.getRetypePassword())) {
            return ResponseEntity.badRequest().body(ResponseObject.builder()
                            .status(HttpStatus.BAD_REQUEST)
                            .data(null)
                            .message("password is not match")
                            .build());
        }

        AuthenticationResponse  registerResponse = userService.createUser(userDTO);

        return ResponseEntity.ok(ResponseObject.builder()
                        .status(HttpStatus.CREATED)
                        .data(registerResponse)
                        .message("Account registration successful")
                        .build());
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseObject> login(
            @Valid @RequestBody UserLoginDTO userLoginDTO,
            BindingResult result
    )throws Exception {
        ResponseEntity<ResponseObject> validationResponse = handleValidationErrors(result);

        if(userLoginDTO.getUsername() == null || userLoginDTO.getUsername().trim().isBlank()) {
            return ResponseEntity.badRequest().body(ResponseObject.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .data(null)
                    .message("username is required")
                    .build());
        }

        AuthenticationResponse loginResponse = userService.login(userLoginDTO);

        return ResponseEntity.ok(ResponseObject.builder()
                        .status(HttpStatus.OK)
                        .data(loginResponse)
                        .message("Refresh token successfully")
                        .build());
    }


    private ResponseEntity<ResponseObject> handleValidationErrors(BindingResult result) {
        if(result.hasErrors()){
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body(ResponseObject.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .data(null)
                    .message(errorMessages.toString())
                    .build());
        }
        return null; // No validation errors
    }


}
