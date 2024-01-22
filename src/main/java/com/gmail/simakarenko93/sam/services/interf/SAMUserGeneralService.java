package com.gmail.simakarenko93.sam.services.interf;

import com.gmail.simakarenko93.sam.dto.*;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface SAMUserGeneralService {
    void addUser(SAMUserDTO samUserDTO, List<IncomeDTO> incomeDTOList,
                 List<PlannedSpendingDTO> plannedSpendingDTOList,
                 List<RegularSpendingDTO> regularSpendingDTOList,
                 List<SavingDTO> savingDTOList,
                 List<UnexpectedSpendingDTO> unexpectedSpendingDTOList);
    void update(String email, SAMUserDTO samUserDTO);
    void delete(List<Long> idList);//todo чи вірно перенесла метод в спільний сервіс?
    List<Object> getAllLists(String email, Pageable pageable); //todo
    Long count(String email);
}
