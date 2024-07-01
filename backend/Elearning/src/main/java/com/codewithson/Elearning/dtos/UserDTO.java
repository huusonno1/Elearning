package com.codewithson.Elearning.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    @JsonProperty("username")
    private String username = "";

    @JsonProperty("password")
    @NotBlank(message = "Password cannot be black")
    private String password = "";

    @JsonProperty("retype_password")
    private String retypePassword = "";

    @JsonProperty("email")
    private String email = "";

    @JsonProperty("date_of_birth")
    private Date dateOfBirth;

    @JsonProperty("image_of_user")
    private String imageOfUser;

    @NotNull(message = "Role ID is required")
    @JsonProperty("role_id")
    //role admin not permitted
    private Long roleId;
}
