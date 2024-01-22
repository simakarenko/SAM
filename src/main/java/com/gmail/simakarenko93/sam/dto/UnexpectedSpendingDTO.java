package com.gmail.simakarenko93.sam.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gmail.simakarenko93.sam.model.markers.RegularCategory;
import com.gmail.simakarenko93.sam.model.markers.UnexpectedCategory;

import java.util.Date;

public class UnexpectedSpendingDTO {
    private Long id;
    private UnexpectedCategory unexpectedCategory;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm", timezone = "UTC")
    private Date date;
    private String name;
    private String comment;
    private double sum;

    @JsonCreator
    public UnexpectedSpendingDTO(@JsonProperty(required = true) UnexpectedCategory unexpectedCategory,
                                 @JsonProperty(required = true) Date date,
                                 @JsonProperty(required = true) String name,
                                 @JsonProperty(required = true) String comment,
                                 @JsonProperty(required = true) double sum) {
        this.unexpectedCategory = unexpectedCategory;
        this.date = date;
        this.name = name;
        this.comment = comment;
        this.sum = sum;

    }

    private UnexpectedSpendingDTO(Long id, UnexpectedCategory unexpectedCategory, Date date, String name, String comment, double sum) {
        this.id = id;
        this.unexpectedCategory = unexpectedCategory;
        this.date = date;
        this.name = name;
        this.comment = comment;
        this.sum = sum;
    }

    public static UnexpectedSpendingDTO of(UnexpectedCategory unexpectedCategory, Date date, String name, String comment, double sum) {
        return new UnexpectedSpendingDTO(null, unexpectedCategory, date, name, comment, sum);
    }

    public static UnexpectedSpendingDTO of(Long id, UnexpectedCategory unexpectedCategory, Date date, String name, String comment, double sum) {
        return new UnexpectedSpendingDTO(id, unexpectedCategory, date, name, comment, sum);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UnexpectedCategory getUnexpectedCategory() {
        return unexpectedCategory;
    }

    public void setUnexpectedCategory(UnexpectedCategory unexpectedCategory) {
        this.unexpectedCategory = unexpectedCategory;
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
