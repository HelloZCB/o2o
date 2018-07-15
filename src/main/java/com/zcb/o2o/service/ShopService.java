package com.zcb.o2o.service;

import java.io.InputStream;

import com.zcb.o2o.dto.ShopExecution;
import com.zcb.o2o.entity.Shop;
import com.zcb.o2o.exceptions.ShopOperationException;

public interface ShopService {
	ShopExecution addShop(Shop shop, InputStream shopImgInputStream, String fileName) throws ShopOperationException;
}
