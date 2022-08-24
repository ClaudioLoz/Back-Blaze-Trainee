package com.blaze.demo.service;

import com.blaze.demo.domain.model.company.Shop;
import com.blaze.demo.rest.requests.ShopRegisterRequest;

public interface ShopService {
 Shop registerShop (ShopRegisterRequest shopRegisterRequest);

}
