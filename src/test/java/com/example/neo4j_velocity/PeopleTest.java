package com.example.neo4j_velocity;

import com.example.neo4j_velocity.services.PeopleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Neo4jVelocityApplication .class)
public class PeopleTest {

        @Autowired
        private PeopleService peopleService;

        @Test
        public void testInitData(){
            peopleService.initData();
        }

        @Test
        public void testSearch(){
                peopleService.findFriendsByName("李四");
        }


}
