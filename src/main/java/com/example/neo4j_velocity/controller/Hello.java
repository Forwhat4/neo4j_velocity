package com.example.neo4j_velocity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Hello {

    @RequestMapping("/hello")
    public String sayHello(Model model){
        model.addAttribute("name","lisi");

        return "templates/hello";
    }



}
