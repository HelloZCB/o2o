package com.zcb.o2o.service;

import com.zcb.o2o.entity.ProductCategory;

import java.util.List;

public interface ProductCategoryService {
    /**
     * 查询某个店铺下的所有商品类别信息
     * @param shopId
     * @return
     */
    List<ProductCategory> getProductCategoryList(long shopId);
}
