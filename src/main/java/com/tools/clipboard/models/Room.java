package com.tools.clipboard.models;

import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;
import java.util.ArrayList;
import java.util.List;

@Data
public class Room {

    private String id;

    private List<User> users = new ArrayList<>();

    public Room addUser(User user) {
        users.add(user);
        return this;
    }

    public static String generateId() {
        return RandomStringUtils.randomAlphabetic(4).toUpperCase();
    }
}
