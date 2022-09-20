package com.blaze.demo.repository;

import com.blaze.demo.domain.model.generic.CompanyBaseModel;
import com.blaze.demo.rest.responses.SearchResult;
import org.bson.types.ObjectId;

import java.util.HashMap;
import java.util.List;

public interface CompanyBaseRepository<T extends CompanyBaseModel> extends BaseRepository<T> {
    Iterable<T> listBefore(long beforeDate);
    Iterable<T> list(String companyId);

    Iterable<T> listWithProjections(String companyId, String projection);

    <E extends CompanyBaseModel> Iterable<E> list(String companyId, Class<E> clazz);

    Iterable<T> listSort(String companyId, String sortOption);

    Iterable<T> listWithOptions(String companyId, String projections);

    HashMap<String, T> listAsMap(String companyId);

    HashMap<String, T> listAsMapWithProjections(String companyId, String projection);

    Iterable<T> listAll(String companyId);

    HashMap<String, T> listAllAsMap(String companyId);

    Iterable<T> listAllByShop(String companyId, String shopId);

    HashMap<String, T> listAllByShopAsMap(String companyId, String shopId);

    Iterable<T> listAllByShopById(String companyId, String shopId, String id);

    HashMap<String, T> listAllByIdAsMap(String companyId, String shopId, String id);

    Iterable<T> findItemsIn(String companyId, List<ObjectId> ids);

    <E extends CompanyBaseModel> Iterable<E> findItemsIn(String companyId, List<ObjectId> ids, Class<E> clazz);

    Iterable<T> findItemsIn(String companyId, List<ObjectId> ids, String projection);

    HashMap<String, T> findItemsInAsMap(String companyId, List<ObjectId> ids);

    Iterable<T> list(String companyId, List<ObjectId> ids);

    Iterable<T> list(List<ObjectId> ids, String projection);

    Iterable<T> list(String companyId, List<ObjectId> ids, String projections);

    HashMap<String, T> listAsMap(String companyId, List<ObjectId> ids);

    HashMap<String, T> listAsMap(List<ObjectId> ids, String projection);

    HashMap<String, T> listAsMap(String companyId, List<ObjectId> ids, String projections);

    T get(String companyId, String id);

    T get(String companyId, String id, String projection);

    boolean exist(String companyId, String id);

    <E extends CompanyBaseModel> E get(String companyid, String id, Class<E> clazz);

    <E extends CompanyBaseModel> E getDefaultRegion(String companyId, Class<E> clazz);

    long count(String companyId);

    T update(String companyId, String entityId, T pojo);

    void removeById(String companyId, String entityId);

    void removeByIdSetState(String companyId, String entityId);

    void removeAllSetState(String companyId);


    SearchResult<T> findItems(String companyId, int skip, int limit);

    <E extends CompanyBaseModel> SearchResult<E> findItems(String companyId, int skip, int limit, Class<E> clazz);

    SearchResult<T> findItemsWithSort(String companyId, String sort, int skip, int limit);

    SearchResult<T> findItemsWithSortAndProjections(String companyId, int skip, int limit, String sort, String projections);

    SearchResult<T> findItemsWithSort(String companyId, List<ObjectId> ids, String sort, int skip, int limit);

    SearchResult<T> findItems(String companyId, int skip, int limit, String projections);

    <E extends T> SearchResult<E> findItems(String companyId, int skip, int limit, String projections, Class<E> clazz);

    Long countItemsIn(String companyId, List<ObjectId> documentIds);

    SearchResult<T> findItemsInShopsActive(String companyId, List<String> shopIds, int skip, int limit);

    <E extends T> SearchResult<E> findItemsInShops(String companyId, List<String> shopIds, int skip, int limit, Class<E> clazz);

    Iterable<T> findItemsBySKU(String companyId, List<String> skus);

    Iterable<T> getAllDeleted(String companyId, String projection);
}
