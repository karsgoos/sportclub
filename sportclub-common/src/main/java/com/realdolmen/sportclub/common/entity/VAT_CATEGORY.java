package com.realdolmen.sportclub.common.entity;

public enum VAT_CATEGORY {
    ZERO(0), TWENTYONE(21);

    private int numberValue;

    VAT_CATEGORY(int numberValue) {
        this.numberValue = numberValue;
    }

    public int getNumberValue() {
        return numberValue;
    }
}
