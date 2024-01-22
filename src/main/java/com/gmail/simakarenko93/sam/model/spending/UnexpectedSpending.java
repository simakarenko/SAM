package com.gmail.simakarenko93.sam.model.spending;

import com.gmail.simakarenko93.sam.dto.UnexpectedSpendingDTO;
import com.gmail.simakarenko93.sam.model.SAMUser;
import com.gmail.simakarenko93.sam.model.markers.UnexpectedCategory;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class UnexpectedSpending {
    @Id
    @GeneratedValue
    private Long id;
    @Enumerated(EnumType.STRING)
    private UnexpectedCategory unexpectedCategory;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    private String name;
    private String comment;
    private double sum;
    @ManyToOne
    @JoinColumn(name = "SAMUser_id")
    private SAMUser samUser;

    public UnexpectedSpending(UnexpectedCategory unexpectedCategory, Date date, String name, String comment, double sum) {
        this.unexpectedCategory = unexpectedCategory;
        this.date = date;
        this.name = name;
        this.comment = comment;
        this.sum = sum;
    }

    public static UnexpectedSpending of(UnexpectedCategory unexpectedCategory, Date date, String name, String comment, double sum) {
        return new UnexpectedSpending(unexpectedCategory, date, name, comment, sum);
    }

    public UnexpectedSpendingDTO toDTO() {
        return UnexpectedSpendingDTO.of(id, unexpectedCategory, date, name, comment, sum);
    }

    public static UnexpectedSpending fromDTO(UnexpectedSpendingDTO unexpectedSpendingDTO) {
        return UnexpectedSpending.of(unexpectedSpendingDTO.getUnexpectedCategory(), unexpectedSpendingDTO.getDate(),
                unexpectedSpendingDTO.getName(), unexpectedSpendingDTO.getComment(), unexpectedSpendingDTO.getSum());
    }
}
