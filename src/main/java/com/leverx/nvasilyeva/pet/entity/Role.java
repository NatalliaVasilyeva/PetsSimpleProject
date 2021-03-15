package com.leverx.nvasilyeva.pet.entity;


import java.util.Arrays;
import java.util.Locale;

public enum Role {
    ADMIN,
    USER;

    public Role getRole(final String inputRole) {
        return Arrays.stream(Role.values()).anyMatch((t) -> t.name().equals(inputRole.toUpperCase(Locale.ROOT))) ? valueOf(inputRole.toUpperCase(Locale.ROOT)) : null;
    }

}
