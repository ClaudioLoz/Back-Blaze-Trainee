package com.blaze.demo.managed;

import com.blaze.demo.domain.MongoDb;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.mongodb.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.jongo.marshall.jackson.JacksonMapper;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class MongoManager implements MongoDb {

    private static Jongo jongo;

    private static MongoClient client;
    static final Log LOG = LogFactory.getLog(MongoManager.class);

    public MongoManager() {
        LOG.info("Initiating MongoManager");
    }

    @PostConstruct
    public void start() throws Exception {
        if (client != null) return;
        LOG.info("Starting MongoManager");

        client = new MongoClient();

        DB db = client.getDB("Demo-blaze");
        db.setWriteConcern(WriteConcern.ACKNOWLEDGED);
        jongo = new Jongo(db,
                new JacksonMapper.Builder()
                        .registerModule(new JodaModule())
                        .enable(MapperFeature.AUTO_DETECT_GETTERS)
                        .build());
    }

    @PreDestroy
    public void stop() throws Exception {
        LOG.info("Stopping MongoManager - DB");
        if (client != null) {
            client.close();
            client = null;
        }
    }

    @Override
    public MongoCollection getJongoCollection(String collectionName) throws Exception {
        //LOG.debug("Accessing collection: " + collectionName);
        return jongo.getCollection(collectionName);
    }

    @Override
    public DBCollection getDBCollection(String collectionName) throws Exception {
        //LOG.debug("Accessing collection: " + collectionName);

        DB db = client.getDB("Demo-blaze");

        return db.getCollection(collectionName);
    }
    @Override
    public Jongo getJongo() throws Exception {
        return jongo;
    }
    @Override
    public DB getDB() throws Exception {
        return client.getDB("Demo-blaze");
    }

}
