package com.example.neo4j_velocity.controller;

import com.example.neo4j_velocity.domain.User;
import com.example.neo4j_velocity.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Hello {

    @Autowired
    private UserService userService;

    @RequestMapping("/hello")
    public String sayHello(Model model){

        User user = userService.getUserByName("John Johnson");

        model.addAttribute("name",user.getName());

        return "templates/hello";
    }



}
