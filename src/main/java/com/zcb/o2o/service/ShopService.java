package com.zcb.o2o.service;

import com.zcb.o2o.dto.ImageHolder;
import com.zcb.o2o.dto.ShopExecution;
import com.zcb.o2o.entity.Shop;
import com.zcb.o2o.exceptions.ShopOperationException;

public interface ShopService {
    ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize);

    Shop getShopById(long shopId);

    ShopExecution modifyShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException;

    ShopExecution addShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException;
}
