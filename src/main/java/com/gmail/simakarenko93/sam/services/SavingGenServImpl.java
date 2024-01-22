package com.gmail.simakarenko93.sam.services;

import com.gmail.simakarenko93.sam.dto.SavingDTO;
import com.gmail.simakarenko93.sam.model.SAMUser;
import com.gmail.simakarenko93.sam.model.Saving;
import com.gmail.simakarenko93.sam.repos.SAMUserRepository;
import com.gmail.simakarenko93.sam.repos.SavingRepository;
import com.gmail.simakarenko93.sam.services.interf.GeneralService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SavingGenServImpl implements GeneralService<SavingDTO> {
    private final SavingRepository savingRepository;
    private final SAMUserRepository samUserRepository;

    public SavingGenServImpl(SavingRepository savingRepository,
                             SAMUserRepository samUserRepository) {
        this.savingRepository = savingRepository;
        this.samUserRepository = samUserRepository;
    }

    @Transactional
    @Override
    public void addObj(String email, SavingDTO savingDTO) {
        SAMUser samUser = samUserRepository.findByEmail(email);
        Saving saving = Saving.fromDTO(savingDTO);
        samUser.addSaving(saving);
        samUserRepository.save(samUser);
    }

    // @Transactional
    //@Override
    //public void delete(List<Long> idList) {
    //  idList.forEach((x) -> savingRepository.deleteById(x));
    //}

    @Transactional
    @Override
    public void update(String email, SavingDTO savingDTO) {
        //todo подумати як зберегти оновленні дані
        //todo і як дістати id?

    }

    @Transactional(readOnly = true)
    @Override
    public List<SavingDTO> getList(String email, Pageable pageable) {
        List<SavingDTO> result = new ArrayList<>();
        List<Saving> savingList = savingRepository.findBySamUserEmail(email, pageable);

        savingList.forEach((x) -> result.add(x.toDTO()));
        return result;
    }

    @Transactional(readOnly = true)
    @Override
    public Long count(String email) {
        return savingRepository.countBySamUserEmail(email);
    }

}
