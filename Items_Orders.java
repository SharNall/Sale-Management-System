package com.example.sale_management;

public class Items_Orders {
    private int ItemsID;//FOREIGN key from items table
    private int OrdersID;//FOREIGN key from orders table
    private int Quantity;
    public Items_Orders(int ItemsID, int OrdersID,int Quantity) {
        this.ItemsID = ItemsID;
        this.OrdersID = OrdersID;
        this.Quantity=Quantity;
    }
	public int getItemsID() {
		return ItemsID;
	}
	public int getOrdersID() {
		return OrdersID;
	}
	public int getQuantity() {
		return Quantity;
	}
	public void setItemsID(int itemsID) {
		ItemsID = itemsID;
	}
	public void setOrdersID(int ordersID) {
		OrdersID = ordersID;
	}
	public void setQuantity(int quantity) {
		Quantity = quantity;
	}
    
}