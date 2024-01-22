package com.gmail.simakarenko93.sam.services;

import com.gmail.simakarenko93.sam.dto.*;
import com.gmail.simakarenko93.sam.model.Income;
import com.gmail.simakarenko93.sam.model.SAMUser;
import com.gmail.simakarenko93.sam.model.Saving;
import com.gmail.simakarenko93.sam.model.Test;
import com.gmail.simakarenko93.sam.model.spending.PlannedSpending;
import com.gmail.simakarenko93.sam.model.spending.RegularSpending;
import com.gmail.simakarenko93.sam.model.spending.UnexpectedSpending;
import com.gmail.simakarenko93.sam.repos.*;
import com.gmail.simakarenko93.sam.services.interf.SAMUserGeneralService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SAMUserGenServImpl implements SAMUserGeneralService {
    private final SAMUserRepository samUserRepository;
    private final IncomeRepository incomeRepository;
    private final PlannedSpendingRepository plannedSpendingRepository;
    private final RegularSpendingRepository regularSpendingRepository;
    private final SavingRepository savingRepository;
    private final UnexpectedSpendingRepository unexpectedSpendingRepository;

    public SAMUserGenServImpl(SAMUserRepository samUserRepository, IncomeRepository incomeRepository,
                              PlannedSpendingRepository plannedSpendingRepository,
                              RegularSpendingRepository regularSpendingRepository,
                              SavingRepository savingRepository,
                              UnexpectedSpendingRepository unexpectedSpendingRepository) {
        this.samUserRepository = samUserRepository;
        this.incomeRepository = incomeRepository;
        this.plannedSpendingRepository = plannedSpendingRepository;
        this.regularSpendingRepository = regularSpendingRepository;
        this.savingRepository = savingRepository;
        this.unexpectedSpendingRepository = unexpectedSpendingRepository;
    }

    @Transactional
    @Override
    public void addUser(SAMUserDTO samUserDTO, List<IncomeDTO> incomeDTOList,
                        List<PlannedSpendingDTO> plannedSpendingDTOList,
                        List<RegularSpendingDTO> regularSpendingDTOList,
                        List<SavingDTO> savingDTOList,
                        List<UnexpectedSpendingDTO> unexpectedSpendingDTOList) {
        if (samUserRepository.existsByEmail(samUserDTO.getEmail()))
            return; // do nothing
        SAMUser samUser = SAMUser.fromDTO(samUserDTO);
        incomeDTOList.forEach((x) -> {
            Income income = Income.fromDTO(x);
            samUser.addIncome(income);
        });
        plannedSpendingDTOList.forEach((x) -> {
            PlannedSpending plannedSpending = PlannedSpending.fromDTO(x);
            samUser.addPlannedSpending(plannedSpending);
        });
        regularSpendingDTOList.forEach((x) -> {
            RegularSpending regularSpending = RegularSpending.fromDTO(x);
            samUser.addRegularSpending(regularSpending);
        });
        savingDTOList.forEach((x) -> {
            Saving saving = Saving.fromDTO(x);
            samUser.addSaving(saving);
        });
        unexpectedSpendingDTOList.forEach((x) -> {
            UnexpectedSpending unexpectedSpending = UnexpectedSpending.fromDTO(x);
            samUser.addUnexpectedSpending(unexpectedSpending);
        });
        samUserRepository.save(samUser);
    }

    @Transactional
    @Override
    public void update(String email, SAMUserDTO samUserDTO) {
        //todo подумати як зберегти оновленні дані
        if (!samUserRepository.existsByEmail(samUserDTO.getEmail()))
            return;
    }

    @Transactional
    @Override
    public void delete(List<Long> idList) {
        idList.forEach((x) -> samUserRepository.deleteById(x));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Object> getAllLists(String email, Pageable pageable) {//todo подумати як повернути змішаний відсортований список
        List<Object> result = new ArrayList<>();
        List<Income> incomeList = incomeRepository.findBySamUserEmail(email, pageable);
        List<PlannedSpending> plannedSpendingList = plannedSpendingRepository.findBySamUserEmail(email, pageable);
        List<RegularSpending> regularSpendingList = regularSpendingRepository.findBySamUserEmail(email, pageable);
        List<UnexpectedSpending> unexpectedSpendingList = unexpectedSpendingRepository.findBySamUserEmail(email, pageable);
        List<Saving> savingList = savingRepository.findBySamUserEmail(email, pageable);

        incomeList.forEach((x) -> result.add(x.toDTO()));
        plannedSpendingList.forEach((x) -> result.add(x.toDTO()));
        regularSpendingList.forEach((x) -> result.add(x.toDTO()));
        unexpectedSpendingList.forEach((x) -> result.add(x.toDTO()));
        savingList.forEach((x) -> result.add(x.toDTO()));
        //todo додати сортування через компаратор по даті коли буде можливість візуально перевірити вірність роботи
        return result;
    }

    @Transactional(readOnly = true) //todo а чи треба взагалі цей метод?
    @Override
    public Long count(String email) {
        long result = incomeRepository.countBySamUserEmail(email)
                + plannedSpendingRepository.countBySamUserEmail(email)
                + regularSpendingRepository.countBySamUserEmail(email)
                + unexpectedSpendingRepository.countBySamUserEmail(email)
                + savingRepository.countBySamUserEmail(email);
        return result;
    }
}
