package com.tools.clipboard.models;

import lombok.Getter;
import lombok.Setter;
import java.util.HashMap;

@Getter
@Setter
public class Server {

    private static Server server = new Server();

    private HashMap<String, Room> rooms = new HashMap<>();

    private Server() {
    }

    public static Server getInstance() {
        return server;
    }

    public boolean roomExists(String code) {
        return rooms.get(code) != null;
    }

    public void createRoom(String code) {
        Room newRoom = new Room();

        rooms.put(code, newRoom);

    }

    public Room addUserTo(User user, String code) {
        return rooms.get(code).addUser(user);
    }

}
