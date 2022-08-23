package com.blaze.demo.domain.model.generic;


public abstract class ShopBaseModel extends CompanyBaseModel {
    protected String shopId;

    public void prepare(String companyId, String shopId) {
        this.shopId = shopId;
//        this.prepare(companyId);
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }


}
