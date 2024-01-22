package com.gmail.simakarenko93.sam.model;

import com.gmail.simakarenko93.sam.dto.IncomeDTO;
import com.gmail.simakarenko93.sam.model.markers.TypeDB;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Income {
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
    @JoinColumn(name = "samUser_id")
    private SAMUser samUser;

    public Income(TypeDB typeDB, Date date, String name, String comment, double sum) {
        this.typeDB = typeDB;
        this.date = date;
        this.name = name;
        this.comment = comment;
        this.sum = sum;
    }

    public static Income of(TypeDB typeDB, Date date, String name, String comment, double sum) {
        return new Income(typeDB, date, name, comment, sum);
    }

    public IncomeDTO toDTO() {
        return IncomeDTO.of(id,typeDB, date, name, comment, sum);
    }

    public static Income fromDTO(IncomeDTO incomeDTO) {
        return Income.of(incomeDTO.getTypeDB(), incomeDTO.getDate(), incomeDTO.getName(),
                incomeDTO.getComment(), incomeDTO.getSum());
    }
    private String parsTypeOut(TypeDB typeDB){
        if(typeDB.toString().equals("PLANNED")){
            return "Запланований дохід";
        } else if (typeDB.toString().equals("REGULAR")) {
            return "Регулярний дохід";
        }else return "Непередбачуваний дохід";
    }
}
