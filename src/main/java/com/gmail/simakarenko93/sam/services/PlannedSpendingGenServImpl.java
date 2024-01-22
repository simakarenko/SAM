package com.gmail.simakarenko93.sam.services;

import com.gmail.simakarenko93.sam.dto.PlannedSpendingDTO;
import com.gmail.simakarenko93.sam.model.SAMUser;
import com.gmail.simakarenko93.sam.model.spending.PlannedSpending;
import com.gmail.simakarenko93.sam.repos.PlannedSpendingRepository;
import com.gmail.simakarenko93.sam.repos.SAMUserRepository;
import com.gmail.simakarenko93.sam.services.interf.GeneralService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlannedSpendingGenServImpl implements GeneralService<PlannedSpendingDTO> {
    private final PlannedSpendingRepository plannedSpendingRepository;
    private final SAMUserRepository samUserRepository;

    public PlannedSpendingGenServImpl(PlannedSpendingRepository plannedSpendingRepository, SAMUserRepository samUserRepository) {
        this.plannedSpendingRepository = plannedSpendingRepository;
        this.samUserRepository = samUserRepository;
    }

    @Transactional
    @Override
    public void addObj(String email, PlannedSpendingDTO plannedSpendingDTO) {
        SAMUser samUser = samUserRepository.findByEmail(email);
        PlannedSpending plannedSpending = PlannedSpending.fromDTO(plannedSpendingDTO);
        samUser.addPlannedSpending(plannedSpending);
        samUserRepository.save(samUser);
    }

    // @Transactional
    //@Override
    //public void delete(List<Long> idList) {
    //  idList.forEach((x) -> plannedSpendingRepository.deleteById(x));
    //}

    @Transactional
    @Override
    public void update(String email, PlannedSpendingDTO plannedSpendingDTO) {
        //todo подумати як зберегти оновленні дані
        //todo і як дістати id?

    }

    @Transactional(readOnly = true)
    @Override
    public List<PlannedSpendingDTO> getList(String email, Pageable pageable) {
        List<PlannedSpendingDTO> result = new ArrayList<>();
        List<PlannedSpending> plannedSpendingList = plannedSpendingRepository.findBySamUserEmail(email, pageable);

        plannedSpendingList.forEach((x) -> result.add(x.toDTO()));
        return result;
    }

    @Transactional(readOnly = true)
    @Override
    public Long count(String email) {
        return plannedSpendingRepository.countBySamUserEmail(email);
    }


}
