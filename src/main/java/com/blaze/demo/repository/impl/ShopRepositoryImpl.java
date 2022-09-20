package com.blaze.demo.repository.impl;

import com.blaze.demo.domain.MongoDb;
import com.blaze.demo.domain.model.company.Shop;
import com.blaze.demo.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

@Lazy
@Repository
public class ShopRepositoryImpl extends CompanyBaseRepositoryImpl<Shop> implements ShopRepository {

    @Autowired
    public ShopRepositoryImpl(MongoDb mongoManager) throws Exception {
        super(Shop.class, mongoManager);
    }



}
