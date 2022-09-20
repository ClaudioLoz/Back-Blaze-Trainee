package com.blaze.demo.repository;

import com.blaze.demo.domain.model.generic.BaseModel;
import com.blaze.demo.rest.responses.SearchResult;
import org.bson.types.ObjectId;
import org.jongo.MongoCollection;

import java.util.HashMap;
import java.util.List;


public interface BaseRepository <T extends BaseModel> extends GenericRepository<T>{

 MongoCollection getMongoCollection();

 Iterable<T> iterator();

 Iterable<T> getAllDeleted();

 List<T> list();

 List<T> listNonProductType();

 List<T> listNonDeleted();

 List<T> listNonDeleted(String projection);

 List<T> listNonDeleted(List<ObjectId> ids);

 HashMap<String, T> listAsMap();

 HashMap<String, T> listAsMap(List<ObjectId> ids);

 HashMap<String, T> listNonDeletedAsMap();

 Iterable<T> findItemsIn(List<ObjectId> ids);
 Iterable<T> findItemsIn(List<ObjectId> ids, String projection);

 HashMap<String, T> findItemsInAsMap(List<ObjectId> ids);
 HashMap<String, T> findItemsInAsMap(List<ObjectId> ids, String projection);

 T getById(String entityId);
 <E extends BaseModel> E getById(String entityId, Class<E> clazz);

 T getById(String entityId, String projection);

 T save(T model);

 void hardRemoveById(List<ObjectId> objectIds);

// @CacheInvalidate
 T update(String entityId, T model);

 boolean exist(String entityId);

 List<T> save(List<T> models);

// @CacheInvalidate
 void removeById(String entityId);

// @CacheInvalidate
 void hardRemoveById(String entityId);

 void updateModified(String entityId);

 Long count();

 Long countActive();

 SearchResult<T> findItems(int skip, int limit);

 SearchResult<T> findItems(String sort, int skip, int limit);

 HashMap<String, T> listNonDeletedActiveAsMap();

 HashMap<String, T> listNonDeletedActiveAsMap(String projection);


}
