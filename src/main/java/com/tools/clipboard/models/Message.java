package com.tools.clipboard.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Data
@RedisHash(timeToLive = 300, value = "message")
public class Message {

    @Id
    private String id;

    private String message;

}
