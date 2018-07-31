package com.zcb.o2o.dao;

import static org.junit.Assert.assertEquals;

import com.zcb.o2o.BaseTest;
import com.zcb.o2o.entity.ProductCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class ProductCategoryDaoTest extends BaseTest {
    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Test
    public void testQueryProductCategoryByShopId() {
        long shopId = 1;
        List<ProductCategory> productCategoryList = productCategoryDao.queryProductCategoryList(shopId);
        System.out.println("个数为：" + productCategoryList.size());
    }

    @Test
    public void testBatchInsert(){
        List<ProductCategory> productCategoryList = new ArrayList<>();
        ProductCategory temp1 = new ProductCategory();
        temp1.setShopId(1L);
        temp1.setProductCategoryName("服饰");
        temp1.setCreateTime(new Date());
        temp1.setPriority(10);
        ProductCategory temp2 = new ProductCategory();
        temp2.setShopId(1L);
        temp2.setProductCategoryName("日用品");
        temp2.setCreateTime(new Date());
        temp2.setPriority(2);
        productCategoryList.add(temp1);
        productCategoryList.add(temp2);
        int effected = productCategoryDao.batchInsertProductCategory(productCategoryList);
        assertEquals(2, 2);
    }
}
