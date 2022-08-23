package com.blaze.demo.domain.model.company;

import com.blaze.demo.domain.annotations.CollectionName;
import com.blaze.demo.domain.model.generic.CompanyBaseModel;

@CollectionName(name = "company_features", uniqueIndexes = {"{companyId:1}"})
public class CompanyFeatures extends CompanyBaseModel {

    private static final int MAX_INVENTORY_PER_SHOP = 10; // First one is the "safe"
    private static final int DEFAULT_TERMINALS = 2;
    private static final int DEFAULT_SHOPS = 1;
    private static final int DEFAULT_EMPLOYEES = 50;
    private int maxTerminals = DEFAULT_TERMINALS;
    private int maxShop = DEFAULT_SHOPS;
    private int maxEmployees = DEFAULT_EMPLOYEES;
    private int maxInventories = MAX_INVENTORY_PER_SHOP;

    private boolean enableMasterCatalog = false;


    public static int getDefaultShops() {
        return DEFAULT_SHOPS;
    }


    public int getMaxEmployees() {
        return maxEmployees;
    }

    public void setMaxEmployees(int maxEmployees) {
        this.maxEmployees = maxEmployees;
    }

    public int getMaxInventories() {
        return maxInventories;
    }

    public void setMaxInventories(int maxInventories) {
        this.maxInventories = maxInventories;
    }

    public static int getDefaultTerminals() {
        return DEFAULT_TERMINALS;
    }

    public int getMaxShop() {
        return maxShop;
    }

    public void setMaxShop(int maxShop) {
        this.maxShop = maxShop;
    }

    public int getMaxTerminals() {
        return maxTerminals;
    }

    public void setMaxTerminals(int maxTerminals) {
        this.maxTerminals = maxTerminals;
    }

    public boolean isEnableMasterCatalog() {
        return enableMasterCatalog;
    }

    public void setEnableMasterCatalog(boolean enableMasterCatalog) {
        this.enableMasterCatalog = enableMasterCatalog;
    }

}
