package com.gmail.simakarenko93.sam.model.spending;

import com.gmail.simakarenko93.sam.dto.PlannedSpendingDTO;
import com.gmail.simakarenko93.sam.model.SAMUser;
import com.gmail.simakarenko93.sam.model.markers.PlannedCategory;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class PlannedSpending {
    @Id
    @GeneratedValue
    private Long id;
    @Enumerated(EnumType.STRING)
    private PlannedCategory plannedCategory;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    private String name;
    private String comment;
    private double sum;
    @ManyToOne
    @JoinColumn(name = "SAMUser_id")
    private SAMUser samUser;

    public PlannedSpending(PlannedCategory plannedCategory, Date date, String name, String comment, double sum) {
        this.plannedCategory = plannedCategory;
        this.date = date;
        this.name = name;
        this.comment = comment;
        this.sum = sum;
    }

    public static PlannedSpending of(PlannedCategory plannedCategory, Date date, String name, String comment, double sum) {
        return new PlannedSpending(plannedCategory, date, name, comment, sum);
    }

    public PlannedSpendingDTO toDTO() {
        return PlannedSpendingDTO.of(id, plannedCategory, date, name, comment, sum);
    }

    public static PlannedSpending fromDTO(PlannedSpendingDTO plannedSpendingDTO) {
        return PlannedSpending.of(plannedSpendingDTO.getPlannedCategory(), plannedSpendingDTO.getDate(),
                plannedSpendingDTO.getName(), plannedSpendingDTO.getComment(), plannedSpendingDTO.getSum());
    }
}
