package com.realdolmen.sportclub.common.builder;

import com.realdolmen.sportclub.common.entity.Guest;
import com.realdolmen.sportclub.common.entity.Role;

public final class GuestBuilder {
    private String email = "guest@gmail.com";
    private String firstName = "Frank";
    private String lastName = "Holman";
    private Role role = new RoleBuilder().build();

    private GuestBuilder() {
    }

    public static GuestBuilder aGuest() {
        return new GuestBuilder();
    }

    public GuestBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public GuestBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public GuestBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public GuestBuilder withRole(Role role) {
        this.role = role;
        return this;
    }

    public Guest build() {
        Guest guest = new Guest();
        guest.setEmail(email);
        guest.setFirstName(firstName);
        guest.setLastName(lastName);
        guest.setRole(role);
        return guest;
    }
}
