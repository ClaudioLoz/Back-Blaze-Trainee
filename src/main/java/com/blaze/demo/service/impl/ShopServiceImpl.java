package com.blaze.demo.service.impl;

import com.blaze.demo.domain.model.company.Company;
import com.blaze.demo.domain.model.company.Shop;
import com.blaze.demo.domain.model.generic.Address;
import com.blaze.demo.repository.ShopRepository;
import com.blaze.demo.rest.requests.ShopRegisterRequest;
import com.blaze.demo.rest.responses.SimpleShopResponse;
import com.blaze.demo.service.CompanyService;
import com.blaze.demo.service.ShopService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

@Lazy
@Service
public class ShopServiceImpl implements ShopService {

    private static final String companyIdTest= "6309751e16e30557bf071f5c";

    @Lazy
    @Autowired
    private ShopRepository shopRepository;

//    @Lazy
//    @Autowired
//    private CompanyService companyService;

    @Override
    public Shop registerShop(ShopRegisterRequest shopRegisterRequest) {

        //TODO: validate request

        Shop shop = new Shop();
        shop.setName(shopRegisterRequest.getName());

        //TODO: get companyID by token, not like this...
        shop.setCompanyId(companyIdTest);

        shop.setShopType(shopRegisterRequest.getShopType());
        shopRepository.save(shop);

        //missing more default settings...
        return shop;
    }


    //TODO: should be by companyId...
    @Override
    public List<SimpleShopResponse> getShops() {
        List<SimpleShopResponse>result = new ArrayList<>();
        Iterable<Shop> auxList = shopRepository.list();
        auxList.forEach(s-> {
                SimpleShopResponse ssr = new SimpleShopResponse();
                ssr.setId(s.getId());
                ssr.setName(s.getName());
                ssr.setActive(s.isActive());
                result.add(ssr);
        });

        return result;
    }

    //TODO: should also be by companyId...
    @Override
    public Shop getFullShopById(String id) {
        return shopRepository.getById(id);
    }

    @Override
    public Shop updateShop(String id, Shop request) {

        //TODO: validate request, for example weird characters...

        //TODO:       Company company = companyRepository.getById(token.getCompanyId());
        //        if (company == null) {
        //            throw new BlazeInvalidArgException("Company", "Company does not exist.");
        //        }
        //TODO: should get companyId by token...       Shop dbShop = shopRepository.get(token.getCompanyId(), shopId);

        Shop dbShop = shopRepository.getById(id);
        if (dbShop == null) {
            //TODO: should be an custom exception ... throw new BlazeInvalidArgException("Shop", "Shop does not exist.");
            throw new InvalidParameterException("Shop does not exist");
        }
        dbShop.setName(request.getName());
        dbShop.setPhoneNumber(request.getPhoneNumber());
        dbShop.setAddress(request.getAddress());
        dbShop.setEmailAdress(request.getEmailAdress());
        dbShop.setShopType(request.getShopType());
        dbShop.setLicense(request.getLicense());

        return shopRepository.update(id,dbShop);
    }
}
