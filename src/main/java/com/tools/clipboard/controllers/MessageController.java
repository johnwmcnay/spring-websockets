package com.tools.clipboard.controllers;

import com.tools.clipboard.models.Message;
import com.tools.clipboard.repos.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    MessageRepository messageDao;

    @GetMapping("/{id}/get")
    @ResponseBody
    public Message getMessage(@PathVariable String id) {
        return messageDao.findById(id);
    }

    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public Message addCustomer(@RequestBody Message message) {
        return messageDao.save(message);
    }

}
