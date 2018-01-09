package com.example.neo4j_velocity.services;

import com.example.neo4j_velocity.domain.Link;
import com.example.neo4j_velocity.domain.People;
import com.example.neo4j_velocity.domain.PpNode;
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
    public Map<String , Object> findFriendsByName(String name) {
        List<People> result = peopleRepository.findFriendsByName(name);

        Map<String , Object> resultData = new HashMap<>();
        Set<PpNode> nodes = new HashSet<>();
        Set<Link> links = new HashSet<>();

        for(People start : result){
            PpNode node = new PpNode();
            if (name.equals(start.getName())){
                node.setCategory(1);
                node.setSymbolSize(80);
            }else{
                node.setCategory(0);
                node.setSymbolSize(50);
            }
            node.setName(start.getId().toString());
            node.setValue(10);
            node.setLabel(start.getName());
            nodes.add(node);

            if(start.getFriends() != null){
                for (People end : start.getFriends()){
                    Link link = new Link();
                    link.setSource(start.getId().toString());
                    link.setTarget(end.getId().toString());
                    link.setValue(10);
                    link.setLabel("is_friend_of");
                    links.add(link);
                }
            }
        }
        System.out.println(nodes.size()+" : "+links.size());
        resultData.put("nodelist",nodes);
        resultData.put("linklist",links);

        return resultData;
    }


}
