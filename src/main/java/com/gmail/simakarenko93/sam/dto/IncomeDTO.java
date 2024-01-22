package com.gmail.simakarenko93.sam.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gmail.simakarenko93.sam.model.markers.TypeDB;

import java.util.Date;

public class IncomeDTO {
    private Long id;
    private TypeDB typeDB;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm", timezone = "UTC")
    private Date date;
    private String name;
    private String comment;
    private double sum;

    @JsonCreator
    public IncomeDTO(@JsonProperty(required = true) TypeDB typeDB,
                     @JsonProperty(required = true) Date date,
                     @JsonProperty(required = true) String name,
                     @JsonProperty(required = true) String comment,
                     @JsonProperty(required = true) double sum) {
        this.typeDB = typeDB;
        this.date = date;
        this.name = name;
        this.comment = comment;
        this.sum = sum;

    }

    private IncomeDTO(Long id, TypeDB typeDB, Date date, String name, String comment, double sum) {
        this.id = id;
        this.typeDB = typeDB;
        this.date = date;
        this.name = name;
        this.comment = comment;
        this.sum = sum;

    }

    public static IncomeDTO of(TypeDB typeDB, Date date, String name, String comment, double sum) {
        return new IncomeDTO(null, typeDB, date, name, comment, sum);
    }

    public static IncomeDTO of(Long id, TypeDB typeDB, Date date, String name, String comment, double sum) {
        return new IncomeDTO(id, typeDB, date, name, comment, sum);
    }

    private String parsTypeOut(TypeDB typeDB) {
        if (typeDB.toString().equals("PLANNED")) {
            return "Запланований дохід";
        } else if (typeDB.toString().equals("REGULAR")) {
            return "Регулярний дохід";
        } else return "Непередбачуваний дохід";
    }

    private TypeDB parsTypeIn(String typeDB) {
        if (typeDB.equals("Запланований дохід")) {
            return TypeDB.PLANNED;
        } else if (typeDB.equals("Регулярний дохід")) {
            return TypeDB.REGULAR;
        } else return TypeDB.UNEXPECTED;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TypeDB getTypeDB() {
        return typeDB;
    }

    public void setTypeDB(TypeDB typeDB) {
        this.typeDB = typeDB;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }
}
