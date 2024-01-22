package com.gmail.simakarenko93.sam.model;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Test {
    private Object object;
    private String text;

    public Test(Object object) {
        this.object=object;
        this.text=object.getClass().toString();
    }
}
