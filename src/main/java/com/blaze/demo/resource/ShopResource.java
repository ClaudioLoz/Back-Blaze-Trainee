package com.blaze.demo.resource;

import com.blaze.demo.domain.model.company.Shop;
import com.blaze.demo.rest.requests.ShopRegisterRequest;
import com.blaze.demo.service.ShopService;
import org.springframework.http.MediaType;

//@Path("/api/v1/shops")
//@Produces(MediaType.APPLICATION_JSON)
//public class ShopResource {
//    @Inject
//    Injector injector;
//
//    @POST
//    @Path("/")
//    @Timed(name = "addShop")
//    public Shop addShop(@Valid ShopRegisterRequest request) {
//        ShopService shopService = injector.getInstance(ShopService.class);
//        return shopService.registerShop(request);
//    }
//
//}
