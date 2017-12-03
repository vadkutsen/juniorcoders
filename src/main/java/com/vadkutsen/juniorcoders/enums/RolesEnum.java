package com.vadkutsen.juniorcoders.enums;


/** Defines the possible roles */
public enum RolesEnum {

    BASIC(1, "ROLE_BASIC"),
    PRO(2, "ROLE_RPO"),
    ADMIN(3, "ROLE_ADMIN");

    private int id;
    private String roleName;

    RolesEnum(int id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    public int getId() {
        return id;
    }

    public String getRoleName() {
        return roleName;
    }
}
