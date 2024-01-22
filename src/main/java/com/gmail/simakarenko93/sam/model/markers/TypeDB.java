package com.gmail.simakarenko93.sam.model.markers;

public enum TypeDB {
    PLANNED, REGULAR, UNEXPECTED;

    @Override
    public String toString() {
        return name();
    }
}
