package com.whalespottingjava.models.enums;

public enum MemberRole {
    ADMIN("ADMIN"),
    USER("USER");

    private final String role;

    MemberRole(final String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return role;
    }
}
