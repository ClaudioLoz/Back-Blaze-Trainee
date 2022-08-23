package com.blaze.demo.domain.model.company;

import com.blaze.demo.domain.annotations.CollectionName;
import com.blaze.demo.domain.model.generic.ShopBaseModel;

@CollectionName(name = "shop_features", uniqueIndexes = {"{shopId:1}"})
public class ShopFeatures extends ShopBaseModel {

}
