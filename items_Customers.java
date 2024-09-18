package com.example.sale_management;

public class items_Customers {
    private int ItemsID;//FOREIGN key from items table
    private int CustomersID;//FOREIGN key from  customers table
    private int Quantity;
    public items_Customers(int ItemsID, int CustomersID,int Quantity) {
        this.ItemsID = ItemsID;
        this.CustomersID = CustomersID;
        this.Quantity=Quantity;
    }
	public int getItemsID() {
		return ItemsID;
	}
	public int getCustomersID() {
		return CustomersID;
	}
	public int getQuantity() {
		return Quantity;
	}
	public void setItemsID(int itemsID) {
		ItemsID = itemsID;
	}
	public void setCustomersID(int customersID) {
		CustomersID = customersID;
	}
	public void setQuantity(int quantity) {
		Quantity = quantity;
	}
    
    
}
