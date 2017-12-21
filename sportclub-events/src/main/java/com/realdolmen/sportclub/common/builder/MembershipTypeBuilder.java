package com.realdolmen.sportclub.common.builder;

import com.realdolmen.sportclub.common.entity.MembershipType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class MembershipTypeBuilder {

    private Long id;

    private String name = "BBB";

    private String description = "Baarden, buik en billen";

    public MembershipTypeBuilder name(String name){
        this.name = name;
        return this;
    }

    public MembershipTypeBuilder description(String description){
        this.description = description;
        return this;
    }

    public MembershipType build(){
        MembershipType membershipType = new MembershipType();
        membershipType.setName(this.name);
        membershipType.setDescription(this.description);
        return membershipType;
    }


}
