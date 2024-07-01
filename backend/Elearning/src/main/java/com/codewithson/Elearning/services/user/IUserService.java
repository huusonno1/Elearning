package com.codewithson.Elearning.services.user;

import com.codewithson.Elearning.dtos.UserDTO;
import com.codewithson.Elearning.dtos.UserLoginDTO;
import com.codewithson.Elearning.response.user.AuthenticationResponse;

public interface IUserService {
    AuthenticationResponse  createUser(UserDTO userDTO) throws Exception;

    AuthenticationResponse  login(UserLoginDTO userLoginDTO) throws Exception;
}
