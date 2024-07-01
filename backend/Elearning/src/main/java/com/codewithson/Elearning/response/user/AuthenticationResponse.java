package com.codewithson.Elearning.response.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("refresh_token")
    private String refreshToken;

    @JsonProperty("message")
    private String message;

    @JsonProperty("tokenType")
    private String tokenType;

    @JsonProperty("username")
    private String username;

    @JsonProperty("roles")
    private String roles;

    @JsonProperty("id")
    private Long id;

}
