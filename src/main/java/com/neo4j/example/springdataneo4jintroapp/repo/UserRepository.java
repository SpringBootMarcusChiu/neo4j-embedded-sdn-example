package com.neo4j.example.springdataneo4jintroapp.repo;

import com.neo4j.example.springdataneo4jintroapp.model.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends Neo4jRepository<User, String> {
    Optional<User> findByUuid(final String uuid);
}
