package com.blaze.demo.resource;

import com.blaze.demo.domain.model.company.Shop;
import com.blaze.demo.rest.requests.ShopRegisterRequest;
import com.blaze.demo.rest.responses.SimpleShopResponse;
import com.blaze.demo.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/api/v1/mgmt/shops")

public class ShopResource {
    @Lazy
    @Autowired
    private ShopService shopService;
    @PostMapping
    public Shop addShop(@RequestBody ShopRegisterRequest request) {
        return shopService.registerShop(request);
    }
    @GetMapping
    public List<SimpleShopResponse> getShops() {
        return shopService.getShops();
    }

    @GetMapping(value="/{id}")
    public Shop getFullShopById(@PathVariable String id) {
        return shopService.getFullShopById(id);
    }
    @PostMapping (value="/{id}")
    public Shop updateShopInfo(@PathVariable String id, @RequestBody Shop request) {
        return shopService.updateShop(id,request);
    }

}
