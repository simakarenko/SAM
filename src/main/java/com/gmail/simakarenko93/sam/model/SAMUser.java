package com.gmail.simakarenko93.sam.model;

import com.gmail.simakarenko93.sam.dto.SAMUserDTO;
import com.gmail.simakarenko93.sam.model.spending.PlannedSpending;
import com.gmail.simakarenko93.sam.model.spending.RegularSpending;
import com.gmail.simakarenko93.sam.model.spending.UnexpectedSpending;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class SAMUser {
    @Id
    @GeneratedValue
    private Long id;
    private String email;
    private String name;
    private String pictureUrl;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "samUser")
    private List<PlannedSpending> plannedSpendingList = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "samUser")
    private List<RegularSpending> regularSpendingList = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "samUser")
    private List<UnexpectedSpending> unexpectedSpendingList = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "samUser")
    private List<Income> incomeList = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "samUser")
    private List<Saving> savingList = new ArrayList<>();

    public SAMUser(String email, String name, String pictureUrl) {
        this.email = email;
        this.name = name;
        this.pictureUrl = pictureUrl;
    }

    public static SAMUser of(String email, String name, String pictureUrl) {
        return new SAMUser(email, name, pictureUrl);
    }

    public static SAMUser fromDTO(SAMUserDTO samUserDTO) {
        return SAMUser.of(samUserDTO.getEmail(), samUserDTO.getName(), samUserDTO.getPictureUrl());
    }

    public SAMUserDTO toDTO() {
        return SAMUserDTO.of(email, name, pictureUrl);
    }

    public void addPlannedSpending(PlannedSpending plannedSpending) {
        plannedSpending.setSamUser(this);
        plannedSpendingList.add(plannedSpending);
    }

    public void addRegularSpending(RegularSpending regularSpending) {
        regularSpending.setSamUser(this);
        regularSpendingList.add(regularSpending);
    }

    public void addUnexpectedSpending(UnexpectedSpending unexpectedSpending) {
        unexpectedSpending.setSamUser(this);
        unexpectedSpendingList.add(unexpectedSpending);
    }

    public void addIncome(Income income) {
        income.setSamUser(this);
        incomeList.add(income);
    }

    public void addSaving(Saving saving) {
        saving.setSamUser(this);
        savingList.add(saving);
    }
}
