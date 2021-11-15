package com.example.fcmserver.domain.repository;

import com.example.fcmserver.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
