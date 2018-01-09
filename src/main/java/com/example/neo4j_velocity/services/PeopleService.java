package com.example.neo4j_velocity.services;

import com.example.neo4j_velocity.domain.Link;
import com.example.neo4j_velocity.domain.People;
import com.example.neo4j_velocity.repositories.PeopleRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class PeopleService {

    @Autowired
    private PeopleRepository peopleRepository;

    public void initData(){

        People p1 = new People("刘一","男",21);
        People p2 = new People("陈二","男",16);
        People p3 = new People("张三","男",23);
        People p4 = new People("李四","男",25);
        People p5 = new People("王五","男",24);
        People p6 = new People("赵六","男",26);
        People p7 = new People("孙七","男",22);
        People p8 = new People("周八","男",14);
        People p9 = new People("吴九","男",18);

        p1.setFriends(Lists.newArrayList(p2,p3,p4));
        p2.setFriends(Lists.newArrayList(p1,p6));
        p3.setFriends(Lists.newArrayList(p1,p4,p9));
        p4.setFriends(Lists.newArrayList(p1,p3,p5));
        p5.setFriends(Lists.newArrayList(p4,p6));
        p6.setFriends(Lists.newArrayList(p2,p7));
        p7.setFriends(Lists.newArrayList(p6,p8));
        p8.setFriends(Lists.newArrayList(p7));
        p9.setFriends(Lists.newArrayList(p3));


        peopleRepository.save(Lists.newArrayList(p1,p2,p3,p4,p5,p6,p7,p8,p9));
    }

    @Transactional(readOnly = true)
    public void findFriendsByName(String name) {
        List<People> result = peopleRepository.findFriendsByName(name);

        Set<People> nodes = new HashSet<>();
        Set<Link> links = new HashSet<>();

        for(People start : result){
            nodes.add(start);
            if(start.getFriends() != null){
                for (People end : start.getFriends()){
                    nodes.add(end);

                    Link link = new Link();
                    link.setStartNode(start.getName());
                    link.setEndNode(end.getName());
                    links.add(link);
                }
            }
        }
        System.out.println(nodes.size()+" : "+links.size());
    }


}
