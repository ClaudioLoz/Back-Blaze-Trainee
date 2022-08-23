package com.blaze.demo;

import com.blaze.demo.domain.model.company.Company;
import com.fasterxml.jackson.databind.MapperFeature;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.WriteConcern;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.jongo.marshall.jackson.JacksonMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {

        System.out.println("Starting MongoManager");

        DB db = new MongoClient().getDB("Demo-blaze");
        db.setWriteConcern(WriteConcern.ACKNOWLEDGED);
        Jongo jongo = new Jongo(db);
        MongoCollection friends = jongo.getCollection("friends");
        Company company = new Company();
        company.setEmail("asdasd@hotmail.com");
        company.setPhoneNumber("+0162222");
        friends.save(company);

        SpringApplication.run(DemoApplication.class, args);
    }

}
