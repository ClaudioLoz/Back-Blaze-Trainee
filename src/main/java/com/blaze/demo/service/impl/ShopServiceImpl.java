package com.blaze.demo.service.impl;

import com.blaze.demo.domain.model.company.Shop;
import com.blaze.demo.rest.requests.ShopRegisterRequest;
import com.blaze.demo.service.ShopService;

public class ShopServiceImpl implements ShopService {
    @Override
    public Shop registerShop(ShopRegisterRequest shopRegisterRequest) {
        Shop shop = new Shop();
        shop.setName(shopRegisterRequest.getName());
        shop.setCompanyId(shopRegisterRequest.getCompanyId());
        shop.setShopType(shopRegisterRequest.getShopType());

        //and default settings
        return shop;
    }
}
