package com.neo4j.example.springdataneo4jintroapp;

import com.neo4j.example.springdataneo4jintroapp.model.User;
import com.neo4j.example.springdataneo4jintroapp.repo.UserRepository;
import org.neo4j.graphdb.*;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@SpringBootApplication
public class SpringDataNeo4jIntroAppApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataNeo4jIntroAppApplication.class, args);
	}

	@Autowired
	GraphDatabaseService graphDatabaseService;
	@Autowired
	SessionFactory sessionFactory;
	@Autowired
	UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		crudNativeNeo4j(graphDatabaseService);
		crudSpringDataNeo4j();
		crudOgmSessionFactory();
		System.exit(0);
	}

	private void crudNativeNeo4j(GraphDatabaseService graphDb) {
		try (Transaction tx = graphDb.beginTx()) {
			// Database operations go here
			Node firstNode = tx.createNode();
			firstNode.setProperty("message", "Hello, ");
			Node secondNode = tx.createNode();
			secondNode.setProperty("message", "World!");

			Relationship relationship = firstNode.createRelationshipTo(secondNode, RelationshipType.withName("KNOWS"));
			relationship.setProperty("message", "brave Neo4j ");

			tx.commit();
		}
	}

	private void crudSpringDataNeo4j() {
        userRepository.save(User.builder().firstName("Marcus").lastName("Chiu").uuid("uuid").build());
		Optional<User> o = userRepository.findByUuid("uuid");
		System.out.println(o.get().getFirstName());
	}

	private void crudOgmSessionFactory() {
		var session = sessionFactory.openSession();
		var result = session.query("MERGE (n:User {firstName:'George', lastName:'Teka'})", new HashMap<>());
		List<Map<String, Object>> dataList = StreamSupport.stream(result.spliterator(), false)
				.collect(Collectors.toList());
		System.out.println(dataList.toString());
	}
}
