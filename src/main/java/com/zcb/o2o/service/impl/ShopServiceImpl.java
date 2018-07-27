package com.zcb.o2o.service.impl;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

import com.zcb.o2o.util.PageCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zcb.o2o.dao.ShopDao;
import com.zcb.o2o.dto.ShopExecution;
import com.zcb.o2o.entity.Shop;
import com.zcb.o2o.enums.ShopStateEnum;
import com.zcb.o2o.exceptions.ShopOperationException;
import com.zcb.o2o.service.ShopService;
import com.zcb.o2o.util.ImageUtil;
import com.zcb.o2o.util.PathUtil;

@Service
public class ShopServiceImpl implements ShopService {

	@Autowired
	private ShopDao shopDao;

	@Override
	@Transactional
	public ShopExecution addShop(Shop shop, InputStream shopImgInputStream, String fileName)
			throws ShopOperationException {
		// 空值判断
		if (shop == null) {
			return new ShopExecution(ShopStateEnum.NULL_SHOP);
		}
		try {
			// 给店铺信息赋值
			shop.setEnableStatus(0);
			shop.setCreateTime(new Date());
			shop.setLastEditTime(new Date());
			shop.setAdvice(ShopStateEnum.stateOf(0).getStateInfo());
			// 添加店铺信息
			int effectedNum = shopDao.insertShop(shop);
			if (effectedNum <= 0) {
				throw new ShopOperationException("店铺创建失败");
			} else {
				if (shopImgInputStream != null) {
					// 存储图片
					try {
						addShopImg(shop, shopImgInputStream, fileName);

					} catch (Exception e) {
						e.printStackTrace();
						throw new RuntimeException("addShopImg error: " + e.getMessage());
					}
					// 更新店铺图片地址
					effectedNum = shopDao.updateShop(shop);
					if (effectedNum <= 0) {
						throw new ShopOperationException("更新图片地址失败");
					}
				}
			}

		} catch (Exception e) {
			throw new RuntimeException("addShop error: " + e.getMessage());
		}
		return new ShopExecution(ShopStateEnum.CHECK, shop);
	}

	private void addShopImg(Shop shop, InputStream shopImgInputStream, String fileName) {
		// 获取shop图片目录的相对值路径
		String dest = PathUtil.getShopImagePath(shop.getShopId());
		String shopImgAddr = ImageUtil.generateThumbnail(shopImgInputStream, fileName, dest);
		shop.setShopImg(shopImgAddr);
	}

	@Override
	public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize) {
		int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
		ShopExecution se = new ShopExecution();
		if (shopCondition == null) {
			se.setState(ShopStateEnum.NULL_SHOP.getState());
		}else {
			List<Shop> shopList = shopDao.queryShopList(shopCondition, rowIndex, pageSize);
			int count = shopDao.queryShopCount(shopCondition);
			if (shopList != null) {
				se.setShopList(shopList);
				se.setCount(count);
			} else {
				se.setState(ShopStateEnum.INNER_ERROR.getState());
			}
		}
		return se;
	}

	@Override
	public Shop getShopById(long shopId) {
		return shopDao.queryByShopId(shopId);
	}

	@Override
	public ShopExecution modifyShop(Shop shop, InputStream shopImgInputStream, String fileName)
			throws ShopOperationException {
		if (shop == null || shop.getShopId() == null) {
			return new ShopExecution(ShopStateEnum.NULL_SHOP);
		} else {
			try {
				// 1判断是否需要处理图片
				if (shopImgInputStream != null && fileName != null && !"".equals(fileName)) {
					Shop tempShop = shopDao.queryByShopId(shop.getShopId());
					if (tempShop.getShopImg() != null) {
						ImageUtil.deleteFileOfPath(tempShop.getShopImg());
					}
					addShopImg(shop, shopImgInputStream, fileName);
				}
				// 2更新店铺信息
				shop.setLastEditTime(new Date());
				int effectNum = shopDao.updateShop(shop);
				if (effectNum <= 0) {
					return new ShopExecution(ShopStateEnum.INNER_ERROR);
				} else {
					shop = shopDao.queryByShopId(shop.getShopId());
					return new ShopExecution(ShopStateEnum.SUCCESS, shop);
				}
			} catch (Exception e) {
				throw new ShopOperationException("modifyShop error: " + e.getMessage());
			}
		}
	}
}
