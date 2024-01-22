package com.gmail.simakarenko93.sam.repos;

import com.gmail.simakarenko93.sam.model.spending.RegularSpending;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegularSpendingRepository extends JpaRepository<RegularSpending, Long> {
    List<RegularSpending> findBySamUserEmail(String email, Pageable pageable);
    Long countBySamUserEmail(String email);//todo і шо це, якось має бути по іншому
}
