package com.zcb.o2o.service.impl;

import com.zcb.o2o.dao.ProductDao;
import com.zcb.o2o.dao.ProductImgDao;
import com.zcb.o2o.dto.ImageHolder;
import com.zcb.o2o.dto.ProductExecution;
import com.zcb.o2o.entity.Product;
import com.zcb.o2o.entity.ProductImg;
import com.zcb.o2o.enums.ProductStateEnum;
import com.zcb.o2o.exceptions.ProductOperationException;
import com.zcb.o2o.service.ProductService;
import com.zcb.o2o.util.ImageUtil;
import com.zcb.o2o.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProductImgDao productImgDao;

    @Override
    @Transactional
    // 1.处理缩略图，获取缩略图相对路径并赋值给product
    // 2.往tb_product写入商品信息，获取productId
    // 3.结合productId批量处理商品详情图
    // 4.将商品详情图列表批量插入tb_product_img中
    public ProductExecution addProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgHolderList) throws ProductOperationException {
        // 空值判断
        if (product != null && product.getShop() != null && product.getShop().getShopId() != null) {
            // 给商品设置上默认属性
            product.setCreateTime(new Date());
            product.setLastEditTime(new Date());
            // 默认为上架的状态
            product.setEnableStatus(1);
            // 若商品缩略图不为空则添加
            if (thumbnail != null) {
                addThumbnail(product, thumbnail);
            }
            // 创建商品信息
            try {
                int effectedNum = productDao.insertProduct(product);
                if (effectedNum <= 0) {
                    throw new ProductOperationException("创建商品失败");
                }
            } catch (Exception e) {
                throw new ProductOperationException("创建商品失败:" + e.getMessage());
            }
            // 若商品详情图不为空则添加
            if (productImgHolderList != null) {
                addProductImgList(product, productImgHolderList);
            }
            return new ProductExecution(ProductStateEnum.SUCCESS, product);
        } else {
            // 传参为空则返回空值错误信息
            return new ProductExecution(ProductStateEnum.EMPTY);
        }
    }

    /**
     * 批量添加商品图片
     * @param product
     * @param productImgHolderList
     */
    private void addProductImgList(Product product, List<ImageHolder> productImgHolderList) {
        String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
        List<ProductImg> productImgList = new ArrayList<>();
        for (ImageHolder imageHolder :
                productImgHolderList) {
            String imgAddr = ImageUtil.generateNormalImg(imageHolder, dest);
            ProductImg temp = new ProductImg();
            temp.setImgAddr(imgAddr);
            temp.setCreateTime(new Date());
            temp.setProductId(product.getProductId());
            productImgList.add(temp);
        }
        if (productImgList.size() > 0) {
            try {
                int effectedNum = productImgDao.batchInsertProductImg(productImgList);
                if (effectedNum <= 0) {
                    throw new ProductOperationException("创建商品详情图片失败");
                }
            } catch (Exception e) {
                throw new ProductOperationException("创建商品详情图片失败:" + e.getMessage());
            }
        }
    }

    /**
     * 保存商品缩略图
     * @param product
     * @param thumbnail
     */
    private void addThumbnail(Product product, ImageHolder thumbnail) {
        //获取保存相对路径
        String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
        String thumbnailAddr = ImageUtil.generateThumbnail(thumbnail, dest);
        product.setImgAddr(thumbnailAddr);
    }
}
