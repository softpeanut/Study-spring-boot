package com.example.jwtsecurity.domain.repository;

import com.example.jwtsecurity.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String eamil);
    boolean existsByEmail(String email);
}
