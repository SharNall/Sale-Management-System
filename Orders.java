package com.example.sale_management;
import java.util.Date;

public class Orders {
    private int OrdersID;//primary key from orders table
    private String Date;
    private float totalPrice;
    private int Quantity;
    private int MerchantsID;//FOREIGN key from merchants table

    public Orders(String Date,float totalprice, int Quantity, int MerchantsID) {
        this.Date = Date;
        this.totalPrice=totalprice;
        this.Quantity = Quantity;
        this.MerchantsID = MerchantsID;
    }

	public Orders(int ordersID, String date,float totalPrice, int quantity, int merchantsID) {
		super();
		OrdersID = ordersID;
		this.totalPrice=totalPrice;
		Date = date;
		Quantity = quantity;
		MerchantsID = merchantsID;
	}
	
	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getOrdersID() {
		return OrdersID;
	}

	public String getDate() {
		return Date;
	}

	public int getQuantity() {
		return Quantity;
	}

	public int getMerchantsID() {
		return MerchantsID;
	}

	public void setOrdersID(int ordersID) {
		OrdersID = ordersID;
	}

	public void setDate(String date) {
		Date = date;
	}

	public void setQuantity(int quantity) {
		Quantity = quantity;
	}

	public void setMerchantsID(int merchantsID) {
		MerchantsID = merchantsID;
	}
    
}
