package com.gmail.simakarenko93.sam.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gmail.simakarenko93.sam.model.markers.PlannedCategory;
import com.gmail.simakarenko93.sam.model.markers.RegularCategory;
import com.gmail.simakarenko93.sam.model.spending.RegularSpending;

import java.util.Date;

public class RegularSpendingDTO {
    private Long id;
    private RegularCategory regularCategory;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm", timezone = "UTC")
    private Date date;
    private String name;
    private String comment;
    private double sum;

    @JsonCreator
    public RegularSpendingDTO(@JsonProperty(required = true) RegularCategory regularCategory,
                              @JsonProperty(required = true) Date date,
                              @JsonProperty(required = true) String name,
                              @JsonProperty(required = true) String comment,
                              @JsonProperty(required = true) double sum) {
        this.regularCategory = regularCategory;
        this.date = date;
        this.name = name;
        this.comment = comment;
        this.sum = sum;

    }

    private RegularSpendingDTO(Long id, RegularCategory regularCategory, Date date, String name, String comment, double sum) {
        this.id = id;
        this.regularCategory = regularCategory;
        this.date = date;
        this.name = name;
        this.comment = comment;
        this.sum = sum;
    }

    public static RegularSpendingDTO of(RegularCategory regularCategory, Date date, String name, String comment, double sum) {
        return new RegularSpendingDTO(null, regularCategory, date, name, comment, sum);
    }

    public static RegularSpendingDTO of(Long id, RegularCategory regularCategory, Date date, String name, String comment, double sum) {
        return new RegularSpendingDTO(id, regularCategory, date, name, comment, sum);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RegularCategory getRegularCategory() {
        return regularCategory;
    }

    public void setRegularCategory(RegularCategory regularCategory) {
        this.regularCategory = regularCategory;
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
