package com.codewithson.Elearning.services.user;

import com.codewithson.Elearning.components.JwtService;
import com.codewithson.Elearning.dtos.UserDTO;
import com.codewithson.Elearning.dtos.UserLoginDTO;
import com.codewithson.Elearning.exceptions.DataNotFoundException;
import com.codewithson.Elearning.models.Role;
import com.codewithson.Elearning.models.Token;
import com.codewithson.Elearning.models.User;
import com.codewithson.Elearning.repositories.RoleRepo;
import com.codewithson.Elearning.repositories.TokenRepo;
import com.codewithson.Elearning.repositories.UserRepo;
import com.codewithson.Elearning.response.user.AuthenticationResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService implements IUserService{

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final TokenRepo tokenRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    @Transactional
    public AuthenticationResponse createUser(UserDTO userDTO) throws Exception {
        if(!userDTO.getUsername().isBlank() && userRepo.findByUsername(userDTO.getUsername()).isPresent()){
            throw new Exception("username already exists");
        }

        Role role = roleRepo.findById(userDTO.getRoleId())
                .orElseThrow(() -> new DataNotFoundException( "user.login.role_not_exist"));

        User newUser = User.builder()
                .username(userDTO.getUsername())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .imageOfUser(userDTO.getImageOfUser())
                .email(userDTO.getEmail())
                .dateOfBirth(userDTO.getDateOfBirth())
                .active(true)
                .build();
        newUser.setRole(role);

        User savedUser = userRepo.save(newUser);
        String jwtToken = jwtService.generateToken(savedUser);
        String refreshToken = jwtService.generateRefreshToken(savedUser);
        saveUserToken(savedUser, jwtToken);


        return AuthenticationResponse.builder()
                .message("register Successfully")
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .username(savedUser.getUsername())
                .roles(savedUser.getRole().getName())
                .id(savedUser.getId())
                .build();
    }

    @Override
    public AuthenticationResponse login(UserLoginDTO userLoginDTO) throws Exception {

        Optional<User> optionalUser = Optional.empty();
        if(userLoginDTO.getUsername() != null) {
            optionalUser = userRepo.findByUsername(userLoginDTO.getUsername());
        }

        if(optionalUser.isEmpty()){
            throw new DataNotFoundException("don't find user");
        }

        User userDetail = optionalUser.get();

        if(!userDetail.isActive()) {
            throw new DataNotFoundException("user is locked");
        }

        if(!passwordEncoder.matches(userLoginDTO.getPassword() ,userDetail.getPassword())) {
            // you should create a new exception for this
            throw new DataNotFoundException("user wrong password");
        }

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        userLoginDTO.getUsername(),
                        userLoginDTO.getPassword(),
                        userDetail.getAuthorities());

        authenticationManager.authenticate(authenticationToken);

        String jwtToken = jwtService.generateToken(userDetail);
        String refreshToken = jwtService.generateRefreshToken(userDetail);
        revokeAllUserTokens(userDetail);
        saveUserToken(userDetail, jwtToken);

        return AuthenticationResponse.builder()
                .message("Login Successfully")
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .tokenType("BEARER")
                .username(userDetail.getUsername())
                .roles(userDetail.getRole().getName())
                .id(userDetail.getId())
                .build();
    }


    private void saveUserToken(User user, String jwtToken) {
        Token token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType("BEARER")
                .expired(false)
                .revoked(false)
                .build();
        tokenRepo.save(token);
    }

    private void revokeAllUserTokens(User user) {
        List<Token> validUserTokens = tokenRepo.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setRevoked(true);
            token.setExpired(true);
        });
        tokenRepo.saveAll(validUserTokens);
    }

}
