package com.zcb.o2o.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zcb.o2o.BaseTest;
import com.zcb.o2o.entity.ShopCategory;

public class ShopCategoryServiceTest extends BaseTest {
    @Autowired
    private ShopCategoryService shopCategoryService;

    @Test
    public void testGetShopCategoryList() {
        ShopCategory shopCategoryCondition = new ShopCategory();
        List<ShopCategory> shopCategoryList = shopCategoryService.getShopCategoryList(shopCategoryCondition);
        for (ShopCategory shopCategory : shopCategoryList) {
            System.out.println(shopCategory.getShopCategoryName());
        }
    }

}
