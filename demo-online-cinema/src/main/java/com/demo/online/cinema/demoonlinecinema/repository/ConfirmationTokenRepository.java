package com.demo.online.cinema.demoonlinecinema.repository;

import com.demo.online.cinema.demoonlinecinema.entity.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {
    public ConfirmationToken findByConfirmationToken(String confirmationToken);
}
