package com.realdolmen.sportclub.common.entity;

public enum VatCategory {
    ZERO(0), TWENTYONE(21);

    private int numberValue;

    VatCategory(int numberValue) {
        this.numberValue = numberValue;
    }

    public int getNumberValue() {
        return numberValue;
    }
}
