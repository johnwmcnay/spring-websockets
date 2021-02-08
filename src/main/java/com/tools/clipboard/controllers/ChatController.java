package com.tools.clipboard.controllers;

import com.tools.clipboard.models.Message;
import com.tools.clipboard.models.Room;
import com.tools.clipboard.models.Server;
import com.tools.clipboard.models.User;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ChatController {

    @MessageMapping("/chat/{id}")
    @SendTo("/topic/messages/{id}")
    public String send(@PathVariable String id, Message message) {
        return message.getMessage();
    }

    @PostMapping("/room/create")
    @ResponseBody
    public String createRoom(@RequestParam(name = "username") String username) {

        String code;
        Server server = Server.getInstance();

        do {
            code = Room.generateId();
        } while (server.roomExists(code));

        User newUser = new User(username);

        server.createRoom(code);
        Room room = server.addUserTo(newUser, code);

        return code;
    }

    @PostMapping("/room/join/{code}")
    @ResponseBody
    public boolean joinRoom(@PathVariable String code, @RequestParam(name = "username") String username) {

        Server server = Server.getInstance();

        if (server.roomExists(code.toUpperCase())) {

            User newUser = new User(username);

            Room room = server.addUserTo(newUser, code);

            //add user to room's user list and server's master user list

            return true;
        }

        return false;
    }
}
