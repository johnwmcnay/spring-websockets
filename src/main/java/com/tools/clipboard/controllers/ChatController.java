package com.tools.clipboard.controllers;

import com.tools.clipboard.models.Message;
import com.tools.clipboard.models.Room;
import com.tools.clipboard.models.Server;
import com.tools.clipboard.repos.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ChatController {

    @Autowired
    MessageRepository messageDao;

    @MessageMapping("/chat/{id}")
    @SendTo("/topic/messages/{id}")
    public String send(@PathVariable String id, Message message) {
        messageDao.save(message);
        return message.getMessage();
    }

    @PostMapping("/room/create")
    @ResponseBody
    public String createRoom() {

        System.out.println("createRoom()");

        String code;

        do {
            code = Room.generateId();
        } while (Server.roomExists(code));

        System.out.println("code = " + code);
        Server.createRoom(code);

        return code;
    }

    @PostMapping("/room/join/{code}")
    @ResponseBody
    public boolean joinRoom(@PathVariable String code) {
        System.out.println("Join");
        System.out.println("code = " + code);
        System.out.println("Server.roomAlreadyExists(code) = " + Server.roomExists(code));
        return Server.roomExists(code);
    }
}
