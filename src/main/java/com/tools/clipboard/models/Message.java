package com.tools.clipboard.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@Data
@RedisHash("message")
public class Message {

    @Id
    private String id;

    @Indexed
    private String externalId;

    private String message;

}
