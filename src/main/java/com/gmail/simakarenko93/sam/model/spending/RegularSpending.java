package com.gmail.simakarenko93.sam.model.spending;

import com.gmail.simakarenko93.sam.dto.RegularSpendingDTO;
import com.gmail.simakarenko93.sam.model.SAMUser;
import com.gmail.simakarenko93.sam.model.markers.RegularCategory;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class RegularSpending {
    @Id
    @GeneratedValue
    private Long id;
    @Enumerated(EnumType.STRING)
    private RegularCategory regularCategory;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    private String name;
    private String comment;
    private double sum;
    @ManyToOne
    @JoinColumn(name = "SAMUser_id")
    private SAMUser samUser;

    public RegularSpending(RegularCategory regularCategory, Date date, String name, String comment, double sum) {
        this.regularCategory = regularCategory;
        this.date = date;
        this.name = name;
        this.comment = comment;
        this.sum = sum;
    }

    public static RegularSpending of(RegularCategory regularCategory, Date date, String name, String comment, double sum) {
        return new RegularSpending(regularCategory, date, name, comment, sum);
    }

    public RegularSpendingDTO toDTO() {
        return RegularSpendingDTO.of(id, regularCategory, date, name, comment, sum);
    }

    public static RegularSpending fromDTO(RegularSpendingDTO regularSpendingDTO) {
        return RegularSpending.of(regularSpendingDTO.getRegularCategory(), regularSpendingDTO.getDate(),
                regularSpendingDTO.getName(), regularSpendingDTO.getComment(), regularSpendingDTO.getSum());
    }
}
