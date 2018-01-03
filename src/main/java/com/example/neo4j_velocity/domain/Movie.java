package com.example.neo4j_velocity.domain;

import lombok.Data;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;


@Data
@NodeEntity(label = "MOVIES")
public class Movie {
	public Movie(String name){
		this.name = name;
	}
	@GraphId
	private Long nodeId;
	@Property(name="name")
	private String name;
}