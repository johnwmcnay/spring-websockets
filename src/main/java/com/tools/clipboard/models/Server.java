package com.tools.clipboard.models;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.util.HashMap;

@Data
@RedisHash(value = "server")
public class Server {

    private static HashMap<String, Room> rooms = new HashMap<>();

    public static boolean roomExists(String code) {
        return rooms.get(code) != null;
    }


    public static void createRoom(String code) {
        rooms.put(code, new Room());
    }
}
