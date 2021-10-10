package com.example.mailsender.entity.certification;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CertificationRepository extends CrudRepository<Certification, String> {
    Optional<Certification> findByEmail(String email);
}
