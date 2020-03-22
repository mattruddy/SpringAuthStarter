package com.pwa.store.security;

import com.pwa.store.exception.ServiceException;
import com.pwa.store.model.EndUser;
import com.pwa.store.repository.EndUserRepo;
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
