package com.gmail.simakarenko93.sam.dto.results;

public class BadRequestResult extends ResultDTO {
    public BadRequestResult() {
        super("JSON deserialization failed");
    }
}
