package com.tools.clipboard.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Data
@RedisHash("user")
public class User {

    @Id
    private String id;

    private String username;


}
