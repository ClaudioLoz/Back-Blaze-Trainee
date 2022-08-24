package com.blaze.demo.repository;

import com.blaze.demo.domain.model.company.Shop;
import com.blaze.demo.rest.requests.ShopRegisterRequest;

public interface BaseRepository {
 Shop registerShop (ShopRegisterRequest shopRegisterRequest);

}