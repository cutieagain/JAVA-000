package com.cutie.mybatisdruiddemo.controller;

import com.cutie.mybatisdruiddemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController("/user")
public class UserController {
    @Autowired
    UserService userService;

}
