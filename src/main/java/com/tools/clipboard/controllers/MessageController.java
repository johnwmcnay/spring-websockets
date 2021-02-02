package com.tools.clipboard.controllers;

import com.tools.clipboard.models.Message;
import com.tools.clipboard.repos.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    MessageRepository messageDao;

    @GetMapping("/{id}/get")
    @ResponseBody
    public Message getMessage(@PathVariable String id) {
        return messageDao.findByExternalId(id);
    }

    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public Message addMessage(@RequestBody Message message) {
        return messageDao.save(message);
    }

}
