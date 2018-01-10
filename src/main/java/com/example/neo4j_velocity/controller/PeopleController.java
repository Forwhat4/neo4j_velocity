package com.example.neo4j_velocity.controller;

import com.example.neo4j_velocity.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class PeopleController {

    @Autowired
    private PeopleService peopleService;

    @RequestMapping("/echarts")
    public String goToEChartsPage(){
        return "templates/echarts";
    }

    @RequestMapping("/d3js")
    public String goToD3jsPage(){
        return "templates/d3js";
    }

    @RequestMapping(value = "/rel",method = RequestMethod.POST)
    public @ResponseBody Map<String ,Object> getRelationship(@RequestParam("name") String name){
        System.out.println("name : "+name);
        return peopleService.findFriendsByName(name);
    }


}
