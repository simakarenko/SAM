package com.gmail.simakarenko93.sam.services;

import com.gmail.simakarenko93.sam.dto.IncomeDTO;
import com.gmail.simakarenko93.sam.dto.SavingDTO;
import com.gmail.simakarenko93.sam.model.Income;
import com.gmail.simakarenko93.sam.model.SAMUser;
import com.gmail.simakarenko93.sam.repos.IncomeRepository;
import com.gmail.simakarenko93.sam.repos.SAMUserRepository;
import com.gmail.simakarenko93.sam.services.interf.GeneralService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class IncomeGenServImpl implements GeneralService<IncomeDTO> {
    private final IncomeRepository incomeRepository;
    private final SAMUserRepository samUserRepository;

    public IncomeGenServImpl(IncomeRepository incomeRepository, SAMUserRepository samUserRepository) {
        this.incomeRepository = incomeRepository;
        this.samUserRepository = samUserRepository;
    }

    @Transactional
    @Override
    public void addObj(String email, IncomeDTO incomeDTO) {
        SAMUser samUser = samUserRepository.findByEmail(email);
        Income income = Income.fromDTO(incomeDTO);
        samUser.addIncome(income);
        samUserRepository.save(samUser);
    }

    // @Transactional
    //@Override
    //public void delete(List<Long> idList) {
    //  idList.forEach((x) -> incomeRepository.deleteById(x));
    //}

    @Transactional
    @Override
    public void update(String email, IncomeDTO incomeDTO) {
        //todo подумати як зберегти оновленні дані
        //todo і як дістати id?

    }

    @Transactional(readOnly = true)
    @Override
    public List<IncomeDTO> getList(String email, Pageable pageable) {
        List<IncomeDTO> result = new ArrayList<>();
        List<Income> incomeList = incomeRepository.findBySamUserEmail(email, pageable);

        incomeList.forEach((x) -> result.add(x.toDTO()));
        return result;
    }

    @Transactional(readOnly = true)
    @Override
    public Long count(String email) {
        return incomeRepository.countBySamUserEmail(email);
    }


}
