package com.spring.starter.service;

import com.spring.starter.exception.ServiceException;
import com.spring.starter.model.EndUser;
import com.spring.starter.model.dto.AuthTokenResponse;
import com.spring.starter.model.dto.SignUpRequest;
import com.spring.starter.repository.EndUserRepo;
import com.spring.starter.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AuthService {

    @Autowired private AuthenticationManager authenticationManager;
    @Autowired private EndUserRepo endUserRepo;
    @Autowired private JwtTokenProvider jwtTokenProvider;
    @Autowired private PasswordEncoder passwordEncoder;

    public AuthTokenResponse signup(SignUpRequest signUpRequest) {
        String username = signUpRequest.getUsername().toLowerCase();
        String email = signUpRequest.getEmail().toLowerCase();
        if (endUserRepo.findByUsernameIgnoreCase(username) != null) {
            throw new ServiceException("Invalid username");
        }

        if (endUserRepo.findByEmailIgnoreCase(email) != null) {
            throw new ServiceException("Invalid email");
        }

        EndUser endUser = new EndUser();
        endUser.setUsername(username);
        endUser.setPassDigest(passwordEncoder.encode(signUpRequest.getPassword()));
        endUserRepo.save(endUser);
        return login(username, signUpRequest.getPassword());
    }

    public AuthTokenResponse login(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username.toLowerCase(), password));
            String token = jwtTokenProvider.createToken(username.toLowerCase());
            AuthTokenResponse response = new AuthTokenResponse();
            response.setToken(token);
            return response;
        } catch (AuthenticationException e) {
            throw e;
        }
    }

}
