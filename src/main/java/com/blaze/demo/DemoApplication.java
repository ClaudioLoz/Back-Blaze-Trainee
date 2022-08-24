package com.blaze.demo;

import com.blaze.demo.domain.model.company.Company;
import com.blaze.demo.domain.model.company.Shop;
import com.blaze.demo.domain.model.generic.Address;
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
        MongoCollection companies = jongo.getCollection("companies");
        Company company = new Company();
        company.setEmail("demoSAC@hotmail.com");
        company.setPhoneNumber("+0162222");
        company.setName("Demo SAC");
        company.setWebsite("demoSAC.com");

        Address address = new Address();
        address.setAddress("Mr John Smith. 132, My Street, Kingston");
        address.setCity("Kingston");
        address.setState("New York");
        address.setZipCode("01");
        company.setAddress(address);

        companies.save(company);

        Shop shop = new Shop();
        shop.setName(company.getName());
        shop.setCompanyId(company.getId());
        shop.setShopType(Shop.ShopType.Medicinal);
        MongoCollection shops= jongo.getCollection("shops");
        shops.save(shop);

        SpringApplication.run(DemoApplication.class, args);
    }

}
