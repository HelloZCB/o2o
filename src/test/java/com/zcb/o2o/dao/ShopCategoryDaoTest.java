package com.zcb.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zcb.o2o.BaseTest;
import com.zcb.o2o.entity.ShopCategory;

public class ShopCategoryDaoTest extends BaseTest{
	
	@Autowired
	private ShopCategoryDao shopCategoryDao;
	
	@Test
	public void testQueryShopCategory() {
		List<ShopCategory> shopCategoryList = shopCategoryDao.queryShopCategory(new ShopCategory());
		assertEquals(2, shopCategoryList.size());
		for (ShopCategory shopCategory : shopCategoryList) {
			System.out.println(shopCategory.getShopCategoryName());
		}
		
		ShopCategory testShopCategory = new ShopCategory();
		ShopCategory parentShopCategory = new ShopCategory();
		parentShopCategory.setShopCategoryId(1L);
		testShopCategory.setParent(parentShopCategory);
		shopCategoryList = shopCategoryDao.queryShopCategory(testShopCategory);
		assertEquals(1, shopCategoryList.size());
		for (ShopCategory shopCategory : shopCategoryList) {
			System.out.println(shopCategory.getShopCategoryName());
		}
	}
}
