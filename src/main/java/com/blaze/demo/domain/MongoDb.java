package com.blaze.demo.domain;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import org.jongo.Jongo;
import org.jongo.MongoCollection;


public interface MongoDb {
    MongoCollection getJongoCollection(String collectionName) throws Exception;

    DBCollection getDBCollection(String collectionName) throws Exception;

    Jongo getJongo() throws Exception;

    DB getDB() throws Exception;
}
