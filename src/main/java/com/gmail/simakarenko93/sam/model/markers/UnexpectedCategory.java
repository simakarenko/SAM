package com.gmail.simakarenko93.sam.model.markers;

public enum UnexpectedCategory {
    ENTERTAINMENT, TOYS, HOUSEHOLD_APPLIANCES, REPAIR, VOLUNTEERING, TAXI,
    IMPULSE_PURCHASES, FOOD, CLOTHES, TREATMENT, ZOO, HOLIDAYS, FOR_HOME;

    @Override
    public String toString() {
        return name();
    }
}
