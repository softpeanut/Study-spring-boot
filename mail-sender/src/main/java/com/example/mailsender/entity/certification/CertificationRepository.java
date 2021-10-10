package com.example.mailsender.entity.certification;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CertificationRepository extends JpaRepository<Certification, String> {
    Optional<Certification> findByEmail(String email);
}
