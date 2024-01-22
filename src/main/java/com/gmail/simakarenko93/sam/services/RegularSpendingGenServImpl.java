package com.gmail.simakarenko93.sam.services;

import com.gmail.simakarenko93.sam.dto.RegularSpendingDTO;
import com.gmail.simakarenko93.sam.model.SAMUser;
import com.gmail.simakarenko93.sam.model.spending.RegularSpending;
import com.gmail.simakarenko93.sam.repos.RegularSpendingRepository;
import com.gmail.simakarenko93.sam.repos.SAMUserRepository;
import com.gmail.simakarenko93.sam.services.interf.GeneralService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegularSpendingGenServImpl implements GeneralService<RegularSpendingDTO> {
    private final RegularSpendingRepository regularSpendingRepository;
    private final SAMUserRepository samUserRepository;

    public RegularSpendingGenServImpl(RegularSpendingRepository regularSpendingRepository,
                                      SAMUserRepository samUserRepository) {
        this.regularSpendingRepository = regularSpendingRepository;
        this.samUserRepository = samUserRepository;
    }

    @Transactional
    @Override
    public void addObj(String email, RegularSpendingDTO regularSpendingDTO) {
        SAMUser samUser = samUserRepository.findByEmail(email);
        RegularSpending regularSpending = RegularSpending.fromDTO(regularSpendingDTO);
        samUser.addRegularSpending(regularSpending);
        samUserRepository.save(samUser);
    }

    // @Transactional
    //@Override
    //public void delete(List<Long> idList) {
    //  idList.forEach((x) -> regularSpendingRepository.deleteById(x));
    //}

    @Transactional
    @Override
    public void update(String email, RegularSpendingDTO regularSpendingDTO) {
        //todo подумати як зберегти оновленні дані
        //todo і як дістати id?

    }

    @Transactional(readOnly = true)
    @Override
    public List<RegularSpendingDTO> getList(String email, Pageable pageable) {
        List<RegularSpendingDTO> result = new ArrayList<>();
        List<RegularSpending> regularSpendingList = regularSpendingRepository.findBySamUserEmail(email, pageable);

        regularSpendingList.forEach((x) -> result.add(x.toDTO()));
        return result;
    }

    @Transactional(readOnly = true)
    @Override
    public Long count(String email) {
        return regularSpendingRepository.countBySamUserEmail(email);
    }

}
