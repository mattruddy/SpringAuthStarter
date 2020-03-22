package com.pwa.store.service;

import com.pwa.store.exception.ServiceException;
import com.pwa.store.model.EndUser;
import com.pwa.store.model.dto.AuthTokenResponse;
import com.pwa.store.model.dto.SignUpRequest;
import com.pwa.store.repository.EndUserRepo;
import com.pwa.store.security.JwtTokenProvider;
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
