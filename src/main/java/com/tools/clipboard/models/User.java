package com.tools.clipboard.models;

import lombok.Data;

@Data
public class User {

    private String id;

    private String username;

    public User(String username) {
        this.username = username;
    }
}
