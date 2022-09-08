package com.blaze.demo.resource;

import com.blaze.demo.domain.model.company.Shop;
import com.blaze.demo.rest.requests.ShopRegisterRequest;
import com.blaze.demo.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/api/v1/shops")

public class ShopResource {
    @Lazy
    @Autowired
    private ShopService shopService;
    @PostMapping(value = "/addShop")
    public Shop addShop(ShopRegisterRequest request) {
        return shopService.registerShop(request);
    }
    @GetMapping()
    public List<Shop> getShops() {
        return shopService.getShops();
    }

}
