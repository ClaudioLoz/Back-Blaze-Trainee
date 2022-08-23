package com.blaze.demo.domain.model.company;


import com.blaze.demo.domain.annotations.CollectionName;
import com.blaze.demo.domain.model.generic.Address;
import com.blaze.demo.domain.model.generic.BaseModel;

@CollectionName(name = "companies", uniqueIndexes = {"{email:1}"})
public class Company extends BaseModel {
    private String name;
    private String phoneNumber;
    private String email;
    private Address address;
    private boolean active = true;

    private String website;
    private String logoURL ;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getLogoURL() {
        return logoURL;
    }

    public void setLogoURL(String logoURL) {
        this.logoURL = logoURL;
    }
}
