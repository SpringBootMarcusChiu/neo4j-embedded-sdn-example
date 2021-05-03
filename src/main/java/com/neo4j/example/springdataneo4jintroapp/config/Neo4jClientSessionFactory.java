package com.neo4j.example.springdataneo4jintroapp.config;

import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

// MAY UNCOMMENT FOR CUSTOM SDN CONFIGURATION (INSTEAD OF SDN AUTO-CONFIGURATION)
//@org.springframework.context.annotation.Configuration
//public class Neo4jClientSessionFactory {
//
//    @Bean
//    public SessionFactory sessionFactory() {
//        Configuration configuration = new Configuration.Builder()
//                .uri("bolt://localhost")
////                .credentials("neo4j", "password")
//                .build();
//        return new SessionFactory(configuration, "com.neo4j.example.springdataneo4jintroapp.model");
//    }
//}
