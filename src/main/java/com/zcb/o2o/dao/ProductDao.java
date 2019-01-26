package com.zcb.o2o.dao;

import com.zcb.o2o.entity.Product;
import org.apache.ibatis.annotations.Param;

public interface ProductDao {
    /**
     * 插入商品
     *
     * @param product
     * @return
     */
    int insertProduct(Product product);

    /**
     * 通过productId查询唯一的商品信息
     *
     * @param productId
     * @return
     */
    Product queryProductById(long productId);

    /**
     * 更新商品信息
     *
     * @param product
     * @return
     */
    int updateProduct(Product product);

    /**
     * 删除商品
     *
     * @param productId
     * @return
     */
    int deleteProduct(@Param("productId") long productId, @Param("shopId") long shopId);
}
