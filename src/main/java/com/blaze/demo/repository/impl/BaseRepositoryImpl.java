package com.blaze.demo.repository.impl;

import com.blaze.demo.domain.MongoDb;
import com.blaze.demo.domain.annotations.CollectionName;
import com.blaze.demo.domain.model.generic.BaseModel;
import com.blaze.demo.repository.BaseRepository;
import com.blaze.demo.rest.SearchResult;
import com.google.common.collect.Lists;
import com.mongodb.DB;
import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
public class BaseRepositoryImpl<T extends BaseModel> implements BaseRepository<T> {
    final MongoDb mongoManager;

    protected final MongoCollection coll;
    protected final Class<T> entityClazz;
    protected CollectionName collectionName;

    public BaseRepositoryImpl(Class<T> clazz, MongoDb mongoManager) throws Exception {
        this.mongoManager = mongoManager;
        collectionName = clazz.getAnnotation(CollectionName.class);
        coll = mongoManager.getJongoCollection(collectionName.name());
        entityClazz = clazz;
    }

    public Jongo getJongo() {
        try {
            return mongoManager.getJongo();
        } catch (Exception e) {
            return null;
        }
    }

    protected DB getDB() {
        try {
            return mongoManager.getDB();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public MongoCollection getMongoCollection() {
        return coll;
    }

    public T save(T pojo) {
        if (pojo == null) {
            throw new RuntimeException("POJO should not be null.");
        }
        Object id = coll.save(pojo).getUpsertedId();
        if (id != null && id instanceof ObjectId) {
            pojo.setId(id.toString());
        }
        return pojo;
    }


    @Override
    public List<T> list() {
        Iterable<T> items = coll.find().as(entityClazz);
        return Lists.newArrayList(items);
    }

    public List<T> listNonProductType() {
        Iterable<T> items = coll.find("{productSaleType: { $exists: # }}", false).as(entityClazz);
        return Lists.newArrayList(items);
    }

    @Override
    public List<T> listNonDeleted() {
        Iterable<T> items = coll.find("{deleted:false}").as(entityClazz);
        return Lists.newArrayList(items);
    }

    @Override
    public List<T> listNonDeleted(String projection) {
        Iterable<T> items = coll.find("{deleted:false}")
                .projection(projection)
                .as(entityClazz);
        return Lists.newArrayList(items);
    }

    @Override
    public List<T> listNonDeleted(List<ObjectId> ids) {
        Iterable<T> items = coll.find("{deleted:false, _id:{$in:#}}", ids).as(entityClazz);
        return Lists.newArrayList(items);
    }

    @Override
    public HashMap<String, T> listNonDeletedAsMap() {
        Iterable<T> items = listNonDeleted();
        return asMap(items);
    }

    @Override
    public Iterable<T> iterator() {
        Iterable<T> items = coll.find().as(entityClazz);
        return items;
    }

    @Override
    public Iterable<T> getAllDeleted() {
        Iterable<T> items = coll.find("{deleted:true}").as(entityClazz);
        return items;
    }

    @Override
    public HashMap<String, T> listAsMap() {
        Iterable<T> items = list();
        return asMap(items);
    }

    @Override
    public HashMap<String, T> listAsMap(List<ObjectId> ids) {
        Iterable<T> items = listNonDeleted(ids);
        return asMap(items);
    }

    @Override
    public Iterable<T> findItemsIn(List<ObjectId> ids) {
        return coll.find("{_id:{$in:#}}", ids).as(entityClazz);
    }

    @Override
    public Iterable<T> findItemsIn(List<ObjectId> ids, String projection) {
        return coll.find("{_id:{$in:#}}", ids).projection(projection).as(entityClazz);
    }

    @Override
    public HashMap<String, T> findItemsInAsMap(List<ObjectId> ids) {
        return asMap(findItemsIn(ids));
    }

    @Override
    public HashMap<String, T> findItemsInAsMap(List<ObjectId> ids, String projection) {
        return asMap(findItemsIn(ids,projection));
    }

    protected HashMap<String, T> asMap(Iterable<T> items) {
        HashMap<String, T> map = new HashMap<>();
        for (T item : items) {
            map.put(item.getId(), item);
        }
        return map;
    }

    @Override
    public List<T> save(List<T> models) {
        if (models == null || models.size() == 0) {
            return models;
        }
        coll.insert(models.toArray());
        return models;
    }


    @Override
    public void removeById(String entityId) {
        if (entityId != null && ObjectId.isValid(entityId)) {
            // Fake Delete
            coll.update(new ObjectId(entityId)).with("{$set: {deleted:true,modified:#}}", DateTime.now().getMillis());
        }
    }

    @Override
    public void hardRemoveById(String entityId) {
        if (entityId != null && ObjectId.isValid(entityId)) {
            coll.remove(new ObjectId(entityId));
        }
    }

    @Override
    public void hardRemoveById(List<ObjectId> objectIds) {
        if (objectIds != null && !objectIds.isEmpty()) {
            coll.remove("{_id: {$in:#}}", objectIds);
        }
    }

    @Override
    public void updateModified(String entityId) {
        if (entityId != null && ObjectId.isValid(entityId)) {
            coll.update(new ObjectId(entityId)).with("{$set: {modified:#}}", DateTime.now().getMillis());
        }
    }

    public T update(String entityId, T pojo) {
        if (entityId == null || !ObjectId.isValid(entityId) || pojo == null) {
            throw new RuntimeException("Entity or POJO should not be null.");
        }
        pojo.setModified(DateTime.now().getMillis());
        coll.update(new ObjectId(entityId)).with(pojo);
        return pojo;
    }


    public T getById(String id) {
        if (id == null || !ObjectId.isValid(id)) {
            return null;
        }
        return coll.findOne(new ObjectId(id)).as(entityClazz);
    }

    @Override
    public <E extends BaseModel> E getById(String entityId, Class<E> clazz) {
        if (entityId == null || !ObjectId.isValid(entityId)) {
            return null;
        }
        return coll.findOne(new ObjectId(entityId)).as(clazz);
    }

    @Override
    public T getById(String entityId, String projection) {
        if (entityId == null || !ObjectId.isValid(entityId)) {
            return null;
        }
        return coll.findOne(new ObjectId(entityId)).projection(projection).as(entityClazz);
    }

    public boolean exist(String id) {
        if (id == null || !ObjectId.isValid(id)) {
            return false;
        }
        long count = coll.count("{_id:#}", new ObjectId(id));
        return count > 0;
    }

    @Override
    public Long count() {
        return coll.count();
    }

    @Override
    public Long countActive() {
        return coll.count("{deleted:false, active:true}");
    }


    @Override
    public SearchResult<T> findItems(int skip, int limit) {
        if (skip < 0) {
            skip = 0;
        }
        if (limit > 200) {
            limit = 200;
        }


        Iterable<T> items = coll.find("{deleted:false}").skip(skip).limit(limit).as(entityClazz);

        long count = coll.count("{deleted:false}");

        SearchResult<T> results = new SearchResult<>();
        results.setValues(Lists.newArrayList(items));
        results.setSkip(skip);
        results.setLimit(limit);
        results.setTotal(count);
        return results;
    }

    @Override
    public SearchResult<T> findItems(String sort, int skip, int limit) {
        if (skip < 0) {
            skip = 0;
        }
        if (limit > 200) {
            limit = 200;
        }

        Iterable<T> items = coll.find("{deleted:false}").sort(sort).skip(skip).limit(limit).as(entityClazz);

        long count = coll.count("{deleted:false}");

        SearchResult<T> results = new SearchResult<>();
        results.setValues(Lists.newArrayList(items));
        results.setSkip(skip);
        results.setLimit(limit);
        results.setTotal(count);
        return results;
    }


    @Override
    public HashMap<String, T> listNonDeletedActiveAsMap() {
        Iterable<T> items = coll.find("{deleted:false,active:true}").as(entityClazz);
        return asMap(items);
    }

    @Override
    public HashMap<String, T> listNonDeletedActiveAsMap(String projection) {
        Iterable<T> items = coll.find("{deleted:false,active:true}")
                .projection(projection)
                .as(entityClazz);
        return asMap(items);
    }

}
