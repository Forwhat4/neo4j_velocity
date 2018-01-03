package com.example.neo4j_velocity;


import com.example.neo4j_velocity.domain.User;
import com.example.neo4j_velocity.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Neo4jVelocityApplication .class)
public class Neo4jVelocityApplicationTests{
	@Autowired
	private UserService userService;
	/**
	 * 因为是通过http连接到Neo4j数据库的，所以要预先启动Neo4j：neo4j console
	 */
	@Test
	public void testInitData(){
		userService.initData();
	}
	@Test
	public void testGetUserByName(){
		User user = userService.getUserByName("John Johnson");
		System.out.println(user);
	}
}
