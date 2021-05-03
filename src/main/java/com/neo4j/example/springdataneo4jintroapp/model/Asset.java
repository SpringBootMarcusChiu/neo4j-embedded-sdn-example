package com.neo4j.example.springdataneo4jintroapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.id.UuidStrategy;

@Data
@SuperBuilder
@NoArgsConstructor
public class Asset {
    @JsonIgnore
    private Long id;
    @Id
    @GeneratedValue(strategy = UuidStrategy.class)
    private String uuid;
}
