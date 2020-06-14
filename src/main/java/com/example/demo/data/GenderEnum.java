package com.example.demo.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum GenderEnum {

    UNKNOWN(0, "unknown"),
    MALE(1, "male"),
    FEMALE(2, "female");

    private int value;

    private String name;

    GenderEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static GenderEnum findByInt(int value) {
        for (GenderEnum item : GenderEnum.values()) {
            if (item.value == value) {
                return item;
            }
        }

        return UNKNOWN;
    }

    @JsonCreator
    public static GenderEnum findByString(String name) {
        for (GenderEnum item : GenderEnum.values()) {
            if (item.name.equals(name)) {
                return item;
            }
        }

        return UNKNOWN;
    }

    @JsonValue
    @Override
    public String toString() {
        return this.name;
    }

    public int toInt() {
        return this.value;
    }
}
