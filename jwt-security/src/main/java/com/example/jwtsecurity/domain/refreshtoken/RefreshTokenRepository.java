package com.example.jwtsecurity.domain.refreshtoken;

import com.example.jwtsecurity.domain.refreshtoken.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByKey(String key);
}
