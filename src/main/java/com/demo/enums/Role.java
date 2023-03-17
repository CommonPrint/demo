package com.demo.enums;

public enum Role {
    USER,
    ADMIN;
}

//public enum Role implements GrantedAuthority {
//    USER,
//    ADMIN;
//
//    @Override
//    public String getAuthority() {
//        return name();
//    }
//}