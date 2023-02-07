package com.demo.online.cinema.demoonlinecinema.service;

import com.demo.online.cinema.demoonlinecinema.entity.User;
import org.springframework.http.ResponseEntity;

public interface IUserService {
    public ResponseEntity<?> saveUser(User user);

    public ResponseEntity<?> confirmEmail(String confirmationToken);
}
