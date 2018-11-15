package com.zcb.o2o.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

import com.zcb.o2o.dto.ImageHolder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zcb.o2o.BaseTest;
import com.zcb.o2o.dto.ShopExecution;
import com.zcb.o2o.entity.Area;
import com.zcb.o2o.entity.PersonInfo;
import com.zcb.o2o.entity.Shop;
import com.zcb.o2o.entity.ShopCategory;
import com.zcb.o2o.enums.ShopStateEnum;

public class ShopServiceTest extends BaseTest {
    @Autowired
    private ShopService shopService;

    @Test
    public void testAddShop() throws FileNotFoundException {
        Shop shop = new Shop();
        PersonInfo owner = new PersonInfo();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();
        owner.setUserId(1L);
        area.setAreaId(1);
        shopCategory.setShopCategoryId(1L);
        shop.setOwner(owner);
        shop.setShopCategory(shopCategory);
        shop.setArea(area);
        shop.setShopName("店铺测试3");
        shop.setShopDesc("test3");
        shop.setShopAddr("test3");
        shop.setPhone("test3");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(ShopStateEnum.CHECK.getState());
        shop.setAdvice("审核中");
        File shopImg = new File("/Users/zcb/Desktop/xiaohuangren.jpeg");
        InputStream inputStream = new FileInputStream(shopImg);
        ImageHolder imageHolder = new ImageHolder(shopImg.getName(), inputStream);
        ShopExecution shopExecution = shopService.addShop(shop, imageHolder);
        assertEquals(ShopStateEnum.CHECK.getState(), shopExecution.getState());
    }

    @Test
    public void testModifyShop() throws FileNotFoundException {
        Shop shop = new Shop();
        shop.setShopId(14L);
        shop.setShopName("修改后的店铺名称");
        File img = new File("/Users/zcb/Desktop/dabai.jpeg");
        InputStream inputStream = new FileInputStream(img);
        ImageHolder imageHolder = new ImageHolder(img.getName(), inputStream);
        ShopExecution shopExecution = shopService.modifyShop(shop, imageHolder);
        System.out.println("新图片的地址为" + shopExecution.getShop().getShopImg());
    }

    @Test
    public void testGetShopList() {
        Shop shopCondition = new Shop();
        ShopCategory sc = new ShopCategory();
        sc.setShopCategoryId(1L);
        shopCondition.setShopCategory(sc);
        ShopExecution shopExecution = shopService.getShopList(shopCondition, 2, 4);
        System.out.println(shopExecution.getShopList().size());
        System.out.println(shopExecution.getCount());
    }
}
