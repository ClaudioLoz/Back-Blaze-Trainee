package com.blaze.demo.service.impl;

import com.blaze.demo.domain.model.company.Company;
import com.blaze.demo.domain.model.company.Shop;
import com.blaze.demo.domain.model.generic.Address;
import com.blaze.demo.repository.ShopRepository;
import com.blaze.demo.rest.requests.ShopRegisterRequest;
import com.blaze.demo.service.ShopService;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Lazy
@Service
public class ShopServiceImpl implements ShopService {

    @Lazy
    @Autowired
    private ShopRepository shopRepository;

    @Override
    public Shop registerShop(ShopRegisterRequest shopRegisterRequest) {
        Company company = new Company();
        company.setEmail("demoSAC@hotmail.com");
        company.setPhoneNumber("+0162222");
        company.setName("Demo SAC today");
        company.setWebsite("demoSAC.com");

        Address address = new Address();
        address.setAddress("Mr John Smith. 132, My Street, Kingston");
        address.setCity("Kingston");
        address.setState("New York");
        address.setZipCode("01");
        company.setAddress(address);

        Shop shop = new Shop();
        shop.setName(company.getName());
        shop.setCompanyId(company.getId());
        shop.setShopType(Shop.ShopType.Medicinal);
        shopRepository.save(shop);

        //missing default settings...
        return shop;
    }

    @Override
    public List<Shop> getShops() {
        return shopRepository.list();
    }
}
