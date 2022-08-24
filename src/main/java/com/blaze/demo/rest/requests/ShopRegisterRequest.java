package com.blaze.demo.rest.requests;

import com.blaze.demo.domain.model.company.Shop;


public class ShopRegisterRequest {
    private String name;

    private Shop.ShopType shopType = Shop.ShopType.Medicinal;
    private String companyId;

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Shop.ShopType getShopType() {
        return shopType;
    }

    public void setShopType(Shop.ShopType shopType) {
        this.shopType = shopType;
    }
}
