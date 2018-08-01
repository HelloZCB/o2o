package com.zcb.o2o.dao;

import static org.junit.Assert.assertEquals;

import com.zcb.o2o.BaseTest;
import com.zcb.o2o.entity.ProductCategory;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductCategoryDaoTest extends BaseTest {
    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Test
    public void testBQueryProductCategoryByShopId() {
        long shopId = 1;
        List<ProductCategory> productCategoryList = productCategoryDao.queryProductCategoryList(shopId);
        System.out.println("个数为：" + productCategoryList.size());
    }

    @Test
    public void testABatchInsert(){
        List<ProductCategory> productCategoryList = new ArrayList<>();
        ProductCategory temp1 = new ProductCategory();
        temp1.setShopId(1L);
        temp1.setProductCategoryName("服饰");
        temp1.setCreateTime(new Date());
        temp1.setPriority(10);
        ProductCategory temp2 = new ProductCategory();
        temp2.setShopId(1L);
        temp2.setProductCategoryName("类型1");
        temp2.setCreateTime(new Date());
        temp2.setPriority(2);
        productCategoryList.add(temp1);
        productCategoryList.add(temp2);
        int effected = productCategoryDao.batchInsertProductCategory(productCategoryList);
        assertEquals(2, 2);
    }

    @Test
    public void testCDeleteProductCategory(){
        long shopId = 1;
        List<ProductCategory> productCategoryList = productCategoryDao.queryProductCategoryList(shopId);
        for (ProductCategory pc :
                productCategoryList) {
            if (pc.getProductCategoryName().equals("类型1") || pc.getProductCategoryName().equals("服饰")){
                int effectedNum = productCategoryDao.deleteProductCategory(pc.getProductCategoryId(), shopId);
                System.out.println(effectedNum);
                assertEquals(1, effectedNum);
            }
        }
    }
}
