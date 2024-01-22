package com.gmail.simakarenko93.sam.model.markers;

public enum PlannedCategory {
    ZOO, HOUSEHOLD_APPLIANCES, REPAIR, TREATMENT, FOR_HOME, CLOTHES, HOLIDAYS;

    @Override
    public String toString() {
        return name();
    }
}
