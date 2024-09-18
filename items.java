package com.example.sale_management;

import java.util.Date;

public class items {
    private int ItemsID;//primary key from items table
    private int BarcodeNum;
    private String name;
    private String ExpirationDate;
    private float sellingPrice;
    private float buyingPrice;
    private int StoredQuantity;
    private String category;

    public items( int BarcodeNum,String name, String ExpirationDate, float sellingPrice, float buyingPrice,
                 int StoredQuantity, String category) {
        this.BarcodeNum = BarcodeNum;
        this.name = name;
        this.ExpirationDate = ExpirationDate;
        this.sellingPrice = sellingPrice;
        this.buyingPrice = buyingPrice;
        this.StoredQuantity = StoredQuantity;
        this.category = category;
    }
    
	public items(int barcodeNum,int itemsID,  String name, String expirationDate, float sellingPrice, float buyingPrice,
			int storedQuantity, String category) {
		super();
		ItemsID = itemsID;
		BarcodeNum = barcodeNum;
		this.name = name;
		ExpirationDate = expirationDate;
		this.sellingPrice = sellingPrice;
		this.buyingPrice = buyingPrice;
		StoredQuantity = storedQuantity;
		this.category = category;
	}

	public int getItemsID() {
		return ItemsID;
	}

	public int getBarcodeNum() {
		return BarcodeNum;
	}

	public String getName() {
		return name;
	}

	public String getExpirationDate() {
		return ExpirationDate;
	}

	public float getSellingPrice() {
		return sellingPrice;
	}

	public float getBuyingPrice() {
		return buyingPrice;
	}

	public int getStoredQuantity() {
		return StoredQuantity;
	}

	public String getCategory() {
		return category;
	}

	public void setItemsID(int itemsID) {
		ItemsID = itemsID;
	}

	public void setBarcodeNum(int barcodeNum) {
		BarcodeNum = barcodeNum;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setExpirationDate(String expirationDate) {
		ExpirationDate = expirationDate;
	}

	public void setSellingPrice(float sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public void setBuyingPrice(float buyingPrice) {
		this.buyingPrice = buyingPrice;
	}

	public void setStoredQuantity(int storedQuantity) {
		StoredQuantity = storedQuantity;
	}

	public void setCategory(String category) {
		this.category = category;
	}
    
}
