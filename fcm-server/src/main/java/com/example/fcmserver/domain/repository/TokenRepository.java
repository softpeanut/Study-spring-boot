package com.example.fcmserver.domain.repository;

import com.example.fcmserver.domain.entity.Token;
import com.example.fcmserver.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Integer> {
    Token findByUser(User user);
}
