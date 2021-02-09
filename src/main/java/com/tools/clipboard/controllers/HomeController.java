package com.tools.clipboard.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class HomeController {

    @GetMapping("/")
    public String landingPage() {
        return "index";
    }

    @GetMapping("/testing")
    @ResponseBody
     public String test() {

        return "test";
    }

}
