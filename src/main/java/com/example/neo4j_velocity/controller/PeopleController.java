package com.example.neo4j_velocity.controller;

import com.example.neo4j_velocity.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PeopleController {

    @Autowired
    private PeopleService peopleService;

    @RequestMapping("/index")
    public String goToIndexPage(){
        return "templates/index";
    }


    @RequestMapping(value = "/rel",method = RequestMethod.POST)
    public String getRelationship(Model model, @RequestParam("name") String name){
        System.out.println("name : "+name);

        model.addAttribute("resultData","结果");
        return "";
    }


}
