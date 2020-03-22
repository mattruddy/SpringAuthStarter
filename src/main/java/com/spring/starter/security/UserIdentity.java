package com.spring.starter.security;

import com.spring.starter.exception.ServiceException;
import com.spring.starter.model.EndUser;
import com.spring.starter.repository.EndUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UserIdentity {

    private EndUser endUser;
    @Autowired
    private EndUserRepo endUserRepo;

    @Transactional(readOnly = true)
    public void create(String username) {
        EndUser user = endUserRepo.findByUsernameIgnoreCase(username);
        if (user == null) {
            throw new ServiceException("Error");
        }
        this.endUser = user;
    }

    public EndUser getEndUser() {
        return endUser;
    }

    public void setEndUser(EndUser endUser) {
        this.endUser = endUser;
    }
}
