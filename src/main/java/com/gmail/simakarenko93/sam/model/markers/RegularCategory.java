package com.gmail.simakarenko93.sam.model.markers;

public enum RegularCategory {
    FOOD, UTILITIES, ENTERTAINMENT, MEDICINES, HYGIENE, CIGARETTES, BEAUTY, TOYS, ZOO;

    @Override
    public String toString() {
        return name();
    }
}
