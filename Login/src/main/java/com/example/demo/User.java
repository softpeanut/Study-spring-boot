package com.example.demo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    User(String userId, String userPassword) {
        this.userId = userId;
        this.userPassword = userPassword;
    }

    @JsonProperty("userId")
    private String userId;

    @JsonProperty("userPassword")
    private String userPassword;

    public String getUserId() {
        return userId;
    }

    public String getUserPassword() {
        return userPassword;
    }
}
