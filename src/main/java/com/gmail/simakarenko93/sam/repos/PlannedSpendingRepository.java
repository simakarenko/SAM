package com.gmail.simakarenko93.sam.repos;

import com.gmail.simakarenko93.sam.model.spending.PlannedSpending;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlannedSpendingRepository extends JpaRepository<PlannedSpending, Long> {
    List<PlannedSpending> findBySamUserEmail(String email, Pageable pageable);
    Long countBySamUserEmail(String email);//todo і шо це, якось має бути по іншому
}
