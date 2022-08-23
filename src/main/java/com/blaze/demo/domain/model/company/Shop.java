package com.blaze.demo.domain.model.company;

import com.blaze.demo.domain.annotations.CollectionName;
import com.blaze.demo.domain.model.generic.Address;
import com.blaze.demo.domain.model.generic.CompanyBaseModel;

@CollectionName(name = "shops", indexes = {"{companyId:1,delete:1}"})
public class Shop extends CompanyBaseModel {

    public enum ShopType {
        Medicinal,
        Recreational,
        Both;

        public static ShopType toShopType(String name) {
            if (name == null) {
                return Medicinal;
            }
            if (name.equalsIgnoreCase("Medicinal")) {
                return Medicinal;
            }
            if (name.equalsIgnoreCase("Recreational")) {
                return Recreational;
            }

            return Both;
        }
    }

    private String name;
    private ShopType shopType;

    private String phoneNumber;
    private String emailAdress;
    private String license;

    private Address address;
    private double latitude;
    private double longitude;

    private boolean active = true;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ShopType getShopType() {
        return shopType;
    }

    public void setShopType(ShopType shopType) {
        this.shopType = shopType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAdress() {
        return emailAdress;
    }

    public void setEmailAdress(String emailAdress) {
        this.emailAdress = emailAdress;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
