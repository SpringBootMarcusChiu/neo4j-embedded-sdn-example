package com.neo4j.example.springdataneo4jintroapp.config;

import org.neo4j.dbms.api.DatabaseManagementService;
import org.neo4j.dbms.api.DatabaseManagementServiceBuilder;
import org.neo4j.graphdb.GraphDatabaseService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

import static org.neo4j.configuration.GraphDatabaseSettings.DEFAULT_DATABASE_NAME;

@Configuration
public class EmbeddedNeo4jConfig {

    @Bean
    public GraphDatabaseService graphDatabaseService() {
        System.out.println("STARTING Embedded Neo4j");
        DatabaseManagementService managementService = new DatabaseManagementServiceBuilder(new File("embedded_neo4j_database"))
                .loadPropertiesFromFile("neo4j.conf")
                .build();
        GraphDatabaseService graphDb = managementService.database(DEFAULT_DATABASE_NAME);
        registerShutdownHook(managementService);
        System.out.println("STARTED Embedded Neo4j");
        return graphDb;
    }

    private static void registerShutdownHook(final DatabaseManagementService managementService) {
        // Registers a shutdown hook for the Neo4j instance so that it shuts
        // down nicely when the VM exits (even if you "Ctrl-C" the running application)
        Runtime.getRuntime().addShutdownHook(new Thread(managementService::shutdown));
    }
}
