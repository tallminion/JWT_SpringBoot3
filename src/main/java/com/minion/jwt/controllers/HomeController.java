package com.minion.jwt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.minion.jwt.Services.UserService;
import com.minion.jwt.models.User;

@RestController
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private UserService userService;



    //http://localhost:8081/home/users
    @GetMapping("/users")
    public List<User> getUser(){
        System.out.println("getting users");
        return this.userService.getUsers();
    }
}
