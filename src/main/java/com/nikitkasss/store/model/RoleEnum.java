package com.nikitkasss.store.model;

public enum RoleEnum {
    ROLE_SELLER("ROLE_SELLER"),
    ROLE_BUYER("ROLE_BUYER"),
    ROLE_ADMIN("ROLE_ADMIN");

    String value;

    RoleEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
