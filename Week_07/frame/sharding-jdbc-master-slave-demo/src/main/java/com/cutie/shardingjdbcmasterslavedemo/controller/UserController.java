package com.cutie.shardingjdbcmasterslavedemo.controller;

import com.cutie.shardingjdbcmasterslavedemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController("/user")
public class UserController {
    @Autowired
    UserService userService;

}
