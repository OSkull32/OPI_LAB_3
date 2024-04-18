package ru.ifmo.web_lab_3;

import java.io.Serializable;

public enum SelectX implements Serializable {
    MINUS4(-4.0),
    MINUS3(-3.0),
    MINUS2(-2.0),
    MINUS1(-1.0),
    PLUS0(0.0),
    PLUS1(1.0),
    PLUS2(2.0),
    PLUS3(3.0),
    PLUS4(4.0),
    UNSELECTED(null);
    private final Double value;
    SelectX(Double value) {
        this.value = value;
    }

    public Double getValue() {
        return value;
    }
}