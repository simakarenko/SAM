package com.gmail.simakarenko93.sam.services;

import com.gmail.simakarenko93.sam.dto.UnexpectedSpendingDTO;
import com.gmail.simakarenko93.sam.model.SAMUser;
import com.gmail.simakarenko93.sam.model.spending.UnexpectedSpending;
import com.gmail.simakarenko93.sam.repos.SAMUserRepository;
import com.gmail.simakarenko93.sam.repos.UnexpectedSpendingRepository;
import com.gmail.simakarenko93.sam.services.interf.GeneralService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UnexpectedSpendingGenServImpl implements GeneralService<UnexpectedSpendingDTO> {
    private final UnexpectedSpendingRepository unexpectedSpendingRepository;
    private final SAMUserRepository samUserRepository;

    public UnexpectedSpendingGenServImpl(UnexpectedSpendingRepository unexpectedSpendingRepository,
                                         SAMUserRepository samUserRepository) {
        this.unexpectedSpendingRepository = unexpectedSpendingRepository;
        this.samUserRepository = samUserRepository;
    }

    @Transactional
    @Override
    public void addObj(String email, UnexpectedSpendingDTO unexpectedSpendingDTO) {
        SAMUser samUser = samUserRepository.findByEmail(email);
        UnexpectedSpending unexpectedSpending = UnexpectedSpending.fromDTO(unexpectedSpendingDTO);
        samUser.addUnexpectedSpending(unexpectedSpending);
        samUserRepository.save(samUser);
    }

    // @Transactional
    //@Override
    //public void delete(List<Long> idList) {
    //  idList.forEach((x) -> unexpectedSpendingRepository.deleteById(x));
    //}

    @Transactional
    @Override
    public void update(String email, UnexpectedSpendingDTO unexpectedSpendingDTO) {
        //todo подумати як зберегти оновленні дані
        //todo і як дістати id?

    }

    @Transactional(readOnly = true)
    @Override
    public List<UnexpectedSpendingDTO> getList(String email, Pageable pageable) {
        List<UnexpectedSpendingDTO> result = new ArrayList<>();
        List<UnexpectedSpending> unexpectedSpendingList = unexpectedSpendingRepository.findBySamUserEmail(email, pageable);

        unexpectedSpendingList.forEach((x) -> result.add(x.toDTO()));
        return result;
    }

    @Transactional(readOnly = true)
    @Override
    public Long count(String email) {
        return unexpectedSpendingRepository.countBySamUserEmail(email);
    }

}
