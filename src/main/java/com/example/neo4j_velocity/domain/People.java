package com.example.neo4j_velocity.domain;

import lombok.Data;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

@NodeEntity
public class People {

    public People() {

    }

    public People(String name, String sex, Integer age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    @GraphId
    private Long id;

    @Property(name = "name")
    private String name;

    @Property(name = "sex")
    private String sex;

    @Property(name = "age")
    private Integer age;

    @Relationship(type = "IS_FRIEND",direction = Relationship.OUTGOING)
    private List<People> friends;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<People> getFriends() {
        return friends;
    }

    public void setFriends(List<People> friends) {
        this.friends = friends;
    }
}
