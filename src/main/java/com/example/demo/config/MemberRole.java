package com.example.demo.config;

public enum MemberRole {
    USER("USER"),ADMIN("ADMIN");

    private final String typeName;

    private MemberRole(String typeName){
        this.typeName = typeName;
    }

    public String getTypeName(){
        return typeName;
    }
}
