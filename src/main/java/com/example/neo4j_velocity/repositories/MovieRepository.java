package com.example.neo4j_velocity.repositories;

import com.example.neo4j_velocity.domain.Movie;
import com.example.neo4j_velocity.domain.People;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends GraphRepository<Movie> {


}

