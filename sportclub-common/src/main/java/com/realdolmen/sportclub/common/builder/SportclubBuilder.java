package com.realdolmen.sportclub.common.builder;

import com.realdolmen.sportclub.common.entity.Sportclub;

public final class SportclubBuilder {

    private String name = "BikerBoys";

    private SportclubBuilder() {
    }

    public static SportclubBuilder aSportclub() {
        return new SportclubBuilder();
    }


    public SportclubBuilder name(String name) {
        this.name = name;
        return this;
    }

    public Sportclub build() {
        Sportclub sportclub = new Sportclub();
        sportclub.setName(name);
        return sportclub;
    }
}
