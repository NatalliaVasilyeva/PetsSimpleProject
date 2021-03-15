package com.leverx.nvasilyeva.pet.entity;

import java.util.Arrays;
import java.util.Locale;

public enum Size {
    SMALL,
    MEDIUM,
    BIG;

    public Size getDogSize(final String size) {
        return Arrays.stream(Role.values()).anyMatch((t) -> t.name().equals(size.toUpperCase(Locale.ROOT))) ? valueOf(size.toUpperCase(Locale.ROOT)) : null;
    }
}
