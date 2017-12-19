package com.realdolmen.sportclub.common.builder;

import com.realdolmen.sportclub.common.entity.Privilege;
import com.realdolmen.sportclub.common.entity.Role;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

public class RoleBuilder {

    private Long id;

    private String name = "REGISTERED_USER";

    private Set<Privilege> privileges = EnumSet.of(Privilege.CAN_CHANGE_PRIVILEGES);

    public RoleBuilder id(Long id){
        this.id = id;
        return this;
    }

    public RoleBuilder name(String name){
        this.name = name;
        return this;
    }

    public RoleBuilder addPrivilege(Privilege privilege){
        this.privileges.add(privilege);
        this.privileges.add((Privilege.CAN_CHANGE_PRIVILEGES));
        return this;
    }

    public Role build(){
        Role role = new Role();
        role.setName(this.name);
        role.setPrivileges(this.privileges);

        return role;
    }


}
