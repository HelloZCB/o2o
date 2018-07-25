package com.zcb.o2o.entity;

import java.util.Date;

public class Shop {
	private Long shopId;
	private String shopName;
	private String shopDesc;
	private String shopAddr;
	private String phone;
	private String shopImg;
	private Integer priority;
	private Date createTime;
	private Date lastEditTime;
	// -1:disable 0:审核中 1:enable
	private Integer enableStatus;
	// 超级管理员给店家的建议
	private String advice;
	private Area area;
	private PersonInfo owner;
	private ShopCategory shopCategory;

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopDesc() {
		return shopDesc;
	}

	public void setShopDesc(String shopDesc) {
		this.shopDesc = shopDesc;
	}

	public String getShopAddr() {
		return shopAddr;
	}

	public void setShopAddr(String shopAddr) {
		this.shopAddr = shopAddr;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getShopImg() {
		return shopImg;
	}

	public void setShopImg(String shopImg) {
		this.shopImg = shopImg;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastEditTime() {
		return lastEditTime;
	}

	public void setLastEditTime(Date lastEditTime) {
		this.lastEditTime = lastEditTime;
	}

	public Integer getEnableStatus() {
		return enableStatus;
	}

	public void setEnableStatus(Integer enableStatus) {
		this.enableStatus = enableStatus;
	}

	public String getAdvice() {
		return advice;
	}

	public void setAdvice(String advice) {
		this.advice = advice;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public PersonInfo getOwner() {
		return owner;
	}

	public void setOwner(PersonInfo owner) {
		this.owner = owner;
	}

	public ShopCategory getShopCategory() {
		return shopCategory;
	}

	public void setShopCategory(ShopCategory shopCategory) {
		this.shopCategory = shopCategory;
	}

	@Override
	public String toString() {
		return String.format(
				"{shopId:%s, shopName:%s, shopDesc:%s, shopAddr:%s, phone:%s, shopImg:%s, priority:%s, createTime:%s, lastEditTime:%s, enableStatus:%s, advice:%s, area:%s}",
				this.shopId,
				this.shopName,
				this.shopDesc,
				this.shopAddr,
				this.phone,
				this.shopImg,
				this.priority,
				this.createTime,
				this.lastEditTime,
				this.enableStatus,
				this.advice,
				this.area != null ? this.area.toString() : null);
	}
	/*
	public static void main(String[] args) {
		Shop shop = new Shop();
		shop.setShopId(1L);
		Area area = new Area();
		area.setAreaId(1);
		shop.setArea(area);
		System.out.println(shop.toString());
	}*/
}
