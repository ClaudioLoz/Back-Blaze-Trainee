package com.blaze.demo.service;

import com.blaze.demo.domain.model.company.Shop;
import com.blaze.demo.rest.requests.ShopRegisterRequest;
import com.blaze.demo.rest.responses.SimpleShopResponse;

import java.util.List;

public interface ShopService {
 Shop registerShop (ShopRegisterRequest shopRegisterRequest);
 List<SimpleShopResponse> getShops ();

 Shop getFullShopById(String id);

 Shop updateShop(String id, Shop request);
}
