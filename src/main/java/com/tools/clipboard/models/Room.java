package com.tools.clipboard.models;

import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.List;

@Data
@RedisHash(value = "room", timeToLive = 86400)
public class Room {

    @Id
    private String id;

    private List<User> users;

    public static String generateId() {
        return RandomStringUtils.randomAlphabetic(4);
    }
}
