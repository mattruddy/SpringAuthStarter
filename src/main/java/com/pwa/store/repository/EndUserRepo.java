package com.pwa.store.repository;

import com.pwa.store.model.EndUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EndUserRepo extends JpaRepository<EndUser, Long> {
    EndUser findByUsernameIgnoreCase(String username);
    EndUser findByEmailIgnoreCase(String email);
}
