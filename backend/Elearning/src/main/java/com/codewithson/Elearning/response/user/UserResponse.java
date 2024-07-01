package com.codewithson.Elearning.response.user;

import com.codewithson.Elearning.models.Role;
import com.codewithson.Elearning.models.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("username")
    private String username = "";

    @JsonProperty("email")
    private String email = "";

    @JsonProperty("date_of_birth")
    private Date dateOfBirth;

    @JsonProperty("image_of_user")
    private String imageOfUser;

    @JsonProperty("is_active")
    private boolean active;

    @JsonProperty("role")
    private Role role;

    public static UserResponse fromUser(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .dateOfBirth(user.getDateOfBirth())
                .imageOfUser(user.getImageOfUser())
                .active(user.isActive())
                .role(user.getRole())
                .build();
    }

}
