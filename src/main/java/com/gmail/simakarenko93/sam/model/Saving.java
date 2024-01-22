package com.gmail.simakarenko93.sam.model;

import com.gmail.simakarenko93.sam.dto.SavingDTO;
import com.gmail.simakarenko93.sam.model.markers.TypeDB;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Saving {
    @Id
    @GeneratedValue
    private Long id;
    @Enumerated(EnumType.STRING)
    private TypeDB typeDB;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    private String name;
    private String comment;
    private double sum;
    @ManyToOne
    @JoinColumn(name = "SAMUser_id")
    private SAMUser samUser;

    public Saving(TypeDB typeDB, Date date, String name, String comment, double sum) {
        this.typeDB = typeDB;
        this.date = date;
        this.name = name;
        this.comment = comment;
        this.sum = sum;
    }

    public static Saving of(TypeDB typeDB, Date date, String name, String comment, double sum) {
        return new Saving(typeDB, date, name, comment, sum);
    }

    public SavingDTO toDTO() {
        return SavingDTO.of(id, typeDB, date, name, comment, sum);
    }

    public static Saving fromDTO(SavingDTO savingDTO) {
        return Saving.of(savingDTO.getTypeDB(), savingDTO.getDate(), savingDTO.getName(),
                savingDTO.getComment(), savingDTO.getSum());
    }
}
