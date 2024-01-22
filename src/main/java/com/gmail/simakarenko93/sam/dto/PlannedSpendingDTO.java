package com.gmail.simakarenko93.sam.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gmail.simakarenko93.sam.model.markers.PlannedCategory;
import com.gmail.simakarenko93.sam.model.markers.TypeDB;

import java.util.Date;

public class PlannedSpendingDTO {
    private Long id;
    private PlannedCategory plannedCategory;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm", timezone = "UTC")
    private Date date;
    private String name;
    private String comment;
    private double sum;

    @JsonCreator
    public PlannedSpendingDTO(@JsonProperty(required = true) PlannedCategory plannedCategory,
                              @JsonProperty(required = true) Date date,
                              @JsonProperty(required = true) String name,
                              @JsonProperty(required = true) String comment,
                              @JsonProperty(required = true) double sum) {
        this.plannedCategory = plannedCategory;
        this.date = date;
        this.name = name;
        this.comment = comment;
        this.sum = sum;

    }

    private PlannedSpendingDTO(Long id, PlannedCategory plannedCategory, Date date, String name, String comment, double sum) {
        this.id = id;
        this.plannedCategory = plannedCategory;
        this.date = date;
        this.name = name;
        this.comment = comment;
        this.sum = sum;
    }

    public static PlannedSpendingDTO of(PlannedCategory plannedCategory, Date date, String name, String comment, double sum) {
        return new PlannedSpendingDTO(null, plannedCategory, date, name, comment, sum);
    }

    public static PlannedSpendingDTO of(Long id, PlannedCategory plannedCategory, Date date, String name, String comment, double sum) {
        return new PlannedSpendingDTO(id, plannedCategory, date, name, comment, sum);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PlannedCategory getPlannedCategory() {
        return plannedCategory;
    }

    public void setPlannedCategory(PlannedCategory plannedCategory) {
        this.plannedCategory = plannedCategory;
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
