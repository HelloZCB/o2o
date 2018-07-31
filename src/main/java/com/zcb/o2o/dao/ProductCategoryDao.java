package com.zcb.o2o.dao;

import com.zcb.o2o.entity.ProductCategory;

import java.util.List;

public interface ProductCategoryDao {
    /**
     * 通过shopId查询店铺商品类别
     * @param shopId
     * @return
     */
    List<ProductCategory> queryProductCategoryList(long shopId);

    /**
     * 批量插入新增商品类别
     * @param productCategoryList
     * @return
     */
    int batchInsertProductCategory(List<ProductCategory> productCategoryList);
}
