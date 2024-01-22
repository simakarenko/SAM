package com.gmail.simakarenko93.sam.repos;

import com.gmail.simakarenko93.sam.model.SAMUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SAMUserRepository extends JpaRepository<SAMUser, Long> {
    boolean existsByEmail(String email);
    //Long countBySAMUserEmail(String email);
    SAMUser findByEmail(String email);
}
