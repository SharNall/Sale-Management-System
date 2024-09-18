package com.example.sale_management;

import java.util.Date;

public class Ritems {
    private int returnedID;//primary key from returned_items table
    private int quantity;
    private String returnedDate;
    private int itemsID;//FOREIGN key from items table
    private int MerchantsID;//FOREIGN key from  merchants table

    public Ritems(int quantity,String returnedDate, int itemsID, int MerchantsID) {
        this.returnedDate = returnedDate;
        this.quantity= quantity;
        this.itemsID = itemsID;
        this.MerchantsID = MerchantsID;
    }

	public Ritems(int returnedID,int quantity,  String returnedDate, int itemsID, int merchantsID) {
		super();
		this.returnedID = returnedID;
		this.quantity= quantity;
		this.returnedDate = returnedDate;
		this.itemsID = itemsID;
		MerchantsID = merchantsID;
	}
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getReturnedID() {
		return returnedID;
	}

	public String getReturnedDate() {
		return returnedDate;
	}

	public int getItemsID() {
		return itemsID;
	}

	public int getMerchantsID() {
		return MerchantsID;
	}

	public void setReturnedID(int returnedID) {
		this.returnedID = returnedID;
	}

	public void setReturnedDate(String returnedDate) {
		this.returnedDate = returnedDate;
	}

	public void setItemsID(int itemsID) {
		this.itemsID = itemsID;
	}

	public void setMerchantsID(int merchantsID) {
		MerchantsID = merchantsID;
	}
    
}