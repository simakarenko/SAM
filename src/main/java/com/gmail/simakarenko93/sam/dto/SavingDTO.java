package com.gmail.simakarenko93.sam.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gmail.simakarenko93.sam.model.markers.TypeDB;

import java.util.Date;

public class SavingDTO {
    private Long id;
    private TypeDB typeDB;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm", timezone = "UTC")
    private Date date;
    private String name;
    private String comment;
    private double sum;

    @JsonCreator
    public SavingDTO(@JsonProperty(required = true) TypeDB typeDB,
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

    private SavingDTO(Long id, TypeDB typeDB, Date date, String name, String comment, double sum) {
        this.id = id;
        this.typeDB = typeDB;
        this.date = date;
        this.name = name;
        this.comment = comment;
        this.sum = sum;
    }

    public static SavingDTO of(TypeDB typeDB, Date date, String name, String comment, double sum) {
        return new SavingDTO(null, typeDB, date, name, comment, sum);
    }

    public static SavingDTO of(Long id, TypeDB typeDB, Date date, String name, String comment, double sum) {
        return new SavingDTO(id, typeDB, date, name, comment, sum);
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
