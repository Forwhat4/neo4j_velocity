package com.example.neo4j_velocity.repositories;

import com.example.neo4j_velocity.domain.Seen;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeenRepository extends GraphRepository<Seen> {
}
