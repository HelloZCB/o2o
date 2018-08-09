package com.zcb.o2o.service;

import com.zcb.o2o.BaseTest;
import com.zcb.o2o.dto.ImageHolder;
import com.zcb.o2o.dto.ProductExecution;
import com.zcb.o2o.entity.Product;
import com.zcb.o2o.entity.ProductCategory;
import com.zcb.o2o.entity.Shop;
import com.zcb.o2o.enums.ProductStateEnum;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ProductServiceTest extends BaseTest {
    @Autowired
    private ProductService productService;

    @Test
    public void testAddProduct() throws FileNotFoundException {
        Shop shop = new Shop();
        shop.setShopId(1L);
        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryId(1L);
        Product product = new Product();
        product.setShop(shop);
        product.setProductCategory(productCategory);
        product.setCreateTime(new Date());
        product.setLastEditTime(new Date());
        product.setEnableStatus(ProductStateEnum.SUCCESS.getState());
        product.setPriority(20);
        product.setProductName("测试商品2");
        product.setProductDesc("测试商品2");

        // 创建缩略图文件流
        File thumbnailFile = new File("/Users/zcb/Desktop/xiaohuangren.jpeg");
        InputStream is = new FileInputStream(thumbnailFile);
        ImageHolder thumbnail = new ImageHolder(thumbnailFile.getName(), is);
        // 创建两个商品详情图文件流并将他们添加到详情图列表中
        File productImg1 = new File("/Users/zcb/Desktop/xiaohuangren.jpeg");
        InputStream is1 = new FileInputStream(productImg1);
        File productImg2 = new File("/Users/zcb/Desktop/dabai.jpeg");
        InputStream is2 = new FileInputStream(productImg2);
        List<ImageHolder> productImgList = new ArrayList<ImageHolder>();
        productImgList.add(new ImageHolder(productImg1.getName(), is1));
        productImgList.add(new ImageHolder(productImg2.getName(), is2));
        // 添加商品并验证
        ProductExecution pe = productService.addProduct(product, thumbnail, productImgList);
        assertEquals(ProductStateEnum.SUCCESS.getState(), pe.getState());

    }
}
