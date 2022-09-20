package com.blaze.demo.repository.impl;

import com.blaze.demo.domain.MongoDb;
import com.blaze.demo.domain.model.generic.CompanyBaseModel;
import com.blaze.demo.repository.CompanyBaseRepository;
import com.blaze.demo.rest.responses.SearchResult;
import com.google.common.collect.Lists;
import org.bson.types.ObjectId;
import org.joda.time.DateTime;

import java.util.HashMap;
import java.util.List;

public class CompanyBaseRepositoryImpl<T extends CompanyBaseModel> extends BaseRepositoryImpl<T> implements CompanyBaseRepository<T> {
    public CompanyBaseRepositoryImpl(Class<T> clazz, MongoDb mongoManager) throws Exception {
        super(clazz, mongoManager);
    }

    @Override
    public T get(String companyId, String id) {
        if (id == null || !ObjectId.isValid(id)) {
            return null;
        }
        return coll.findOne("{companyId: #,_id:#}", companyId, new ObjectId(id)).as(entityClazz);
    }

    @Override
    public T get(String companyId, String id, String projection) {
        if (id == null || !ObjectId.isValid(id)) {
            return null;
        }
        return coll.findOne("{companyId: #,_id:#}", companyId, new ObjectId(id)).projection(projection).as(entityClazz);
    }

    @Override
    public boolean exist(String companyId, String id) {
        if (id == null || !ObjectId.isValid(id)) return false;
        return coll.findOne("{companyId: #,_id:#}", companyId, new ObjectId(id))
                .projection("{_id:1}").as(entityClazz) != null;
    }

    @Override
    public <E extends CompanyBaseModel> E get(String companyId, String id, Class<E> clazz) {
        if (id == null || !ObjectId.isValid(id)) {
            return null;
        }
        return coll.findOne("{companyId: #,_id:#}", companyId, new ObjectId(id)).as(clazz);
    }

    @Override
    public <E extends CompanyBaseModel> E getDefaultRegion(String companyId, Class<E> clazz) {
        return coll.findOne("{companyId: #, regionDefault: true}", companyId).as(clazz);
    }

    @Override
    public Iterable<T> list(String companyId) {
        return coll.find("{companyId:#, deleted:false}", companyId).as(entityClazz);
    }

    @Override
    public Iterable<T> listWithProjections(String companyId, String projection) {
        return coll.find("{companyId:#, deleted:false}", companyId).projection(projection).as(entityClazz);
    }

    @Override
    public Iterable<T> listBefore(long beforeDate) {
        return coll.find("{modified:{$lt:#}}", beforeDate).as(entityClazz);
    }

    @Override
    public <E extends CompanyBaseModel> Iterable<E> list(String companyId, Class<E> clazz) {
        return coll.find("{companyId:#,deleted:false}", companyId).as(clazz);
    }

    @Override
    public Iterable<T> listSort(String companyId, String sortOption) {
        return coll.find("{companyId:#,deleted:false}", companyId).sort(sortOption).as(entityClazz);
    }

    @Override
    public Iterable<T> listWithOptions(String companyId, String projections) {
        return coll.find("{companyId:#,deleted:false}", companyId).projection(projections).as(entityClazz);
    }

    @Override
    public HashMap<String, T> listAsMap(String companyId) {
        Iterable<T> items = list(companyId);
        return asMap(items);
    }

    @Override
    public HashMap<String, T> listAsMapWithProjections(String companyId, String projection) {
        Iterable<T> items = listWithProjections(companyId, projection);
        return asMap(items);
    }

    @Override
    public Iterable<T> listAll(String companyId) {
        return coll.find("{companyId:#}", companyId).as(entityClazz);
    }

    @Override
    public HashMap<String, T> listAllAsMap(String companyId) {
        Iterable<T> items = listAll(companyId);
        return asMap(items);
    }

    @Override
    public Iterable<T> listAllByShop(String companyId, String shopId) {
        return coll.find("{companyId:#, shopId:#}", companyId, shopId).as(entityClazz);
    }

    @Override
    public HashMap<String, T> listAllByShopAsMap(String companyId, String shopId) {
        Iterable<T> items = listAllByShop(companyId, shopId);
        return asMap(items);
    }

    @Override
    public Iterable<T> listAllByShopById(String companyId, String shopId, String id) {
        return coll.find("{companyId: #,shopId:#, _id:#}", companyId, shopId, new ObjectId(id)).as(entityClazz);
    }

    @Override
    public HashMap<String, T> listAllByIdAsMap(String companyId, String shopId, String id) {
        Iterable<T> items = listAllByShopById(companyId, shopId, id);
        return asMap(items);
    }

    @Override
    public Iterable<T> list(String companyId, List<ObjectId> ids) {
        return coll.find("{companyId:#,_id : {$in: #}}", companyId, ids).as(entityClazz);
    }

    @Override
    public Iterable<T> list(List<ObjectId> ids, String projection) {
        return coll.find("{_id : {$in: #}}", ids).projection(projection).as(entityClazz);
    }

    @Override
    public Iterable<T> list(String companyId, List<ObjectId> ids, String projections) {
        return coll.find("{companyId:#,_id : {$in: #}}", companyId, ids).projection(projections).as(entityClazz);
    }

    @Override
    public HashMap<String, T> listAsMap(String companyId, List<ObjectId> ids) {
        return asMap(list(companyId, ids));
    }

    @Override
    public HashMap<String, T> listAsMap(List<ObjectId> ids, String projection) {
        return asMap(list(ids, projection));
    }

    @Override
    public HashMap<String, T> listAsMap(String companyId, List<ObjectId> ids, String projections) {
        return asMap(list(companyId, ids, projections));
    }

    @Override
    public Iterable<T> findItemsIn(String companyId, List<ObjectId> ids) {
        return coll.find("{companyId:#,_id:{$in:#}}", companyId, ids).as(entityClazz);
    }

    @Override
    public <E extends CompanyBaseModel> Iterable<E> findItemsIn(String companyId, List<ObjectId> ids, Class<E> clazz) {
        return coll.find("{companyId:#,_id:{$in:#}}", companyId, ids).as(clazz);
    }

    @Override
    public Iterable<T> findItemsIn(String companyId, List<ObjectId> ids, String projection) {
        return coll.find("{companyId:#,_id:{$in:#}}", companyId, ids).projection(projection).as(entityClazz);
    }

    @Override
    public HashMap<String, T> findItemsInAsMap(String companyId, List<ObjectId> ids) {
        Iterable<T> items = findItemsIn(companyId, ids);
        return asMap(items);
    }

    @Override
    public T update(String companyId, String entityId, T pojo) {
        pojo.setModified(DateTime.now().getMillis());
        coll.update("{companyId:#,_id: #}", companyId, new ObjectId(entityId)).with(pojo);
        return pojo;
    }

    @Override
    public long count(String companyId) {
        return coll.count("{companyId:#,deleted:false}", companyId);
    }

    @Override
    public void removeById(String companyId, String entityId) {
        coll.update("{companyId:#,_id:#}", companyId, new ObjectId(entityId)).with("{$set: {deleted:true,modified:#}}", DateTime.now().getMillis());
    }

    @Override
    public void removeByIdSetState(String companyId, String entityId) {
        coll.update("{companyId:#,_id:#}", companyId, new ObjectId(entityId)).with("{$set: {deleted:true,active:false,modified:#}}", DateTime.now().getMillis());
    }

    @Override
    public void removeAllSetState(String companyId) {
        coll.update("{companyId:#}", companyId).multi().with("{$set: {deleted:true,active:false,modified:#}}", DateTime.now().getMillis());
    }


    @Override
    public SearchResult<T> findItems(String companyId, int skip, int limit) {
        Iterable<T> items = coll.find("{companyId:#,deleted:false}", companyId).skip(skip).limit(limit).as(entityClazz);

        long count = coll.count("{companyId:#,deleted:false}", companyId);

        SearchResult<T> results = new SearchResult<>();
        results.setValues(Lists.newArrayList(items));
        results.setSkip(skip);
        results.setLimit(limit);
        results.setTotal(count);
        return results;
    }

    @Override
    public <E extends CompanyBaseModel> SearchResult<E> findItems(String companyId, int skip, int limit, Class<E> clazz) {
        Iterable<E> items = coll.find("{companyId:#,deleted:false}", companyId).skip(skip).limit(limit).as(clazz);

        long count = coll.count("{companyId:#,deleted:false}", companyId);

        SearchResult<E> results = new SearchResult<>();
        results.setValues(Lists.newArrayList(items));
        results.setSkip(skip);
        results.setLimit(limit);
        results.setTotal(count);
        return results;
    }

    @Override
    public SearchResult<T> findItemsWithSort(String companyId, String sort, int skip, int limit) {
        Iterable<T> items = coll.find("{companyId:#,deleted:false}", companyId).sort(sort).skip(skip).limit(limit).as(entityClazz);

        long count = coll.count("{companyId:#,deleted:false}", companyId);

        SearchResult<T> results = new SearchResult<>();
        results.setValues(Lists.newArrayList(items));
        results.setSkip(skip);
        results.setLimit(limit);
        results.setTotal(count);
        return results;
    }

    @Override
    public SearchResult<T> findItemsWithSortAndProjections(String companyId, int skip, int limit, String sort, String projections) {
        Iterable<T> items = coll.find("{companyId:#,deleted:false}", companyId).sort(sort).skip(skip).limit(limit).projection(projections).as(entityClazz);

        long count = coll.count("{companyId:#,deleted:false}", companyId);

        SearchResult<T> results = new SearchResult<>();
        results.setValues(Lists.newArrayList(items));
        results.setSkip(skip);
        results.setLimit(limit);
        results.setTotal(count);
        return results;
    }

    @Override
    public SearchResult<T> findItemsWithSort(String companyId, List<ObjectId> ids, String sort, int skip, int limit) {

        Iterable<T> items = coll.find("{companyId:#,_id:{$in:#}}", companyId, ids).sort(sort).skip(skip).limit(limit).as(entityClazz);

        long count = coll.count("{companyId:#,_id:{$in:#}}", companyId, ids);

        SearchResult<T> results = new SearchResult<>();
        results.setValues(Lists.newArrayList(items));
        results.setSkip(skip);
        results.setLimit(limit);
        results.setTotal(count);
        return results;
    }

    @Override
    public SearchResult<T> findItems(String companyId, int skip, int limit, String projections) {
        Iterable<T> items = coll.find("{companyId:#,deleted:false}", companyId).skip(skip).limit(limit).projection(projections).as(entityClazz);

        long count = coll.count("{companyId:#,deleted:false}", companyId);

        SearchResult<T> results = new SearchResult<>();
        results.setValues(Lists.newArrayList(items));
        results.setSkip(skip);
        results.setLimit(limit);
        results.setTotal(count);
        return results;
    }

    @Override
    public <E extends T> SearchResult<E> findItems(String companyId, int skip, int limit, String projections, Class<E> clazz) {
        Iterable<E> items = coll.find("{companyId:#,deleted:false}", companyId).skip(skip).limit(limit).projection(projections).as(clazz);

        long count = coll.count("{companyId:#,deleted:false}", companyId);

        SearchResult<E> results = new SearchResult<>();
        results.setValues(Lists.newArrayList(items));
        results.setSkip(skip);
        results.setLimit(limit);
        results.setTotal(count);
        return results;
    }

    @Override
    public Long countItemsIn(String companyId, List<ObjectId> ids) {
        return coll.count("{companyId:#,_id:{$in:#}}", companyId, ids);
    }

    @Override
    public SearchResult<T> findItemsInShopsActive(String companyId, List<String> shopIds, int skip, int limit) {

        Iterable<T> items = coll.find("{companyId:#,shopId:{$in:#},active:true}", companyId, shopIds).skip(skip).limit(limit).as(entityClazz);

        long count = coll.count("{companyId:#,shopId:{$in:#},active:true}", companyId, shopIds);

        SearchResult<T> results = new SearchResult<>();
        results.setValues(Lists.newArrayList(items));
        results.setSkip(skip);
        results.setLimit(limit);
        results.setTotal(count);
        return results;
    }


    @Override
    public <E extends T> SearchResult<E> findItemsInShops(String companyId, List<String> shopIds, int skip, int limit, Class<E> clazz) {

        Iterable<E> items = coll.find("{companyId:#,shopId:{$in:#}}", companyId, shopIds).skip(skip).limit(limit).as(clazz);

        long count = coll.count("{companyId:#,shopId:{$in:#}}", companyId, shopIds);

        SearchResult<E> results = new SearchResult<>();
        results.setValues(Lists.newArrayList(items));
        results.setSkip(skip);
        results.setLimit(limit);
        results.setTotal(count);

        return results;
    }

    @Override
    public Iterable<T> findItemsBySKU(String companyId, List<String> skus) {
        return coll.find("{companyId:#, deleted:false, sku:{$in:#}}", companyId, skus).as(entityClazz);
    }

    @Override
    public Iterable<T> getAllDeleted(String companyId, String projection) {
        return coll.find("{companyId: #, deleted:true}", companyId)
                .projection(projection)
                .as(entityClazz);
    }
}
