package com.demo.online.cinema.demoonlinecinema.repository;

import com.demo.online.cinema.demoonlinecinema.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public User findByUserEmailIgnoreCase(String userEmail);

    public Boolean existsByUserEmail(String potentialUserEmail);
}