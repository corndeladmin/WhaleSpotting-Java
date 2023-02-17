package com.whalespottingjava.models.requests;

public class MemberRegistrationRequest {
    // TODO 409: Find right library for notnull and notempty
//    @NotNull
//    @NotEmpty
    private String username;

//    @NotNull
//    @NotEmpty
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
