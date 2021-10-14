package com.example.smssender.entity.member;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    Optional<Member> findByPhoneNumber(String phoneNumber);
    Optional<Member> findByName(String name);

}
