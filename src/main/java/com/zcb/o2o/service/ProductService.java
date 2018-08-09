package com.zcb.o2o.service;

import com.zcb.o2o.dto.ImageHolder;
import com.zcb.o2o.dto.ProductExecution;
import com.zcb.o2o.entity.Product;
import com.zcb.o2o.exceptions.ProductOperationException;

import java.util.List;

public interface ProductService {

    ProductExecution addProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgHolderList) throws ProductOperationException;
}
