package com.gmail.simakarenko93.sam.repos;

import com.gmail.simakarenko93.sam.model.Income;
import com.gmail.simakarenko93.sam.model.SAMUser;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IncomeRepository extends JpaRepository<Income, Long> {
    List<Income> findBySamUserEmail(String email, Pageable pageable);
    Long countBySamUserEmail(String email);//todo і шо це, якось має бути по іншому
    Income findById(long id);
}
