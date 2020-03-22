package com.spring.starter.service;

import com.spring.starter.model.EndUser;
import com.spring.starter.repository.EndUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Component
@Transactional
public class MyUserDetailService implements UserDetailsService {

    @Autowired private EndUserRepo endUserRepo;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        EndUser endUser = endUserRepo.findByUsernameIgnoreCase(s);

        if (endUser == null) {
            return new User(
                    "", "",
                    true, true, true, true,
                    Collections.emptyList()
            );
        }

        return new User(
                endUser.getUsername(), endUser.getPassDigest(),
                true, true, true, true,
                Collections.emptyList()
        );
    }
}
