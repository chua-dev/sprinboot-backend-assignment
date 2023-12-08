package com.chua.backendassignment.model.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public enum Gender {
    MALE("M"),
    FEMALE("F");

    private final String genderChar;

    private Gender(String genderChar){
        this.genderChar = genderChar;
    }
}
