package com.nikitkasss.store.model;

public enum RoleEnum {
    SELLER("SELLER"),
    BUYER("BUYER"),
    ADMIN("ADMIN");

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
