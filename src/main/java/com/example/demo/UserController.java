package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserInfoService userInfoService;

    @GetMapping("/")
    public String index() {
        userInfoService.get(1);
        return "Greetings from Spring Boot!";
    }

}