package com.gmail.simakarenko93.sam.dto;

public class SAMUserDTO {
    private final String email;
    private final String name;
    private final String pictureUrl;

    private SAMUserDTO(String email, String name, String pictureUrl) {
        this.email = email;
        this.name = name;
        this.pictureUrl = pictureUrl;
    }

    public static SAMUserDTO of(String email, String name, String pictureUrl) {
        return new SAMUserDTO(email, name, pictureUrl);
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }
}
