package com.realdolmen.sportclub.common.builder;

import com.realdolmen.sportclub.common.entity.Address;

public class AddressBuilder {
    private String street = "Meir";

    private String homeNumber = "1A";

    private String postalCode = "2000";

    private String city = "Antwerpen";

    private String country = "België";

    public AddressBuilder street(String value) {
        street = value;
        return this;
    }

    public AddressBuilder homeNumber(String value) {
        homeNumber = value;
        return this;
    }

    public AddressBuilder postalCode(String value) {
        postalCode = value;
        return this;
    }

    public AddressBuilder city(String value) {
        city = value;
        return this;
    }

    public AddressBuilder country(String value) {
        country = value;
        return this;
    }

    public Address build() {
        Address address = new Address();
        address.setStreet(street);
        address.setHomeNumber(homeNumber);
        address.setPostalCode(postalCode);
        address.setCity(city);
        address.setCountry(country);

        return address;
    }
}