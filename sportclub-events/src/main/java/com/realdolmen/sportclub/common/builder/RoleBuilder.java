package com.realdolmen.sportclub.common.builder;


import com.realdolmen.sportclub.common.entity.Privilege;
import com.realdolmen.sportclub.common.entity.Role;

import java.util.*;

public class RoleBuilder {

    private Long id;

    private String name = "REGISTERED_USER";

    private Set<Privilege> privileges = new LinkedHashSet<>();

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

        return this;
    }

    public Role build(){
        Role role = new Role();
        role.setName(this.name);
        role.setPrivileges(this.privileges);

        return role;
    }


}
