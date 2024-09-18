package com.example.sale_management;



public class Customers {
    private int CustomersID;//primary key in customers table
    private String name;
    private String phone;
    private String address;

    public Customers(String name, String phone, String address) {
        this.name = name;
        this.phone = phone;
        this.address = address;
    }
    
	public Customers(int customersID, String name, String phone, String address) {
		super();
		CustomersID = customersID;
		this.name = name;
		this.phone = phone;
		this.address = address;
	}

	public int getCustomersID() {
		return CustomersID;
	}

	public String getName() {
		return name;
	}

	public String getPhone() {
		return phone;
	}

	public String getAddress() {
		return address;
	}

	public void setCustomersID(int customersID) {
		CustomersID = customersID;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setAddress(String address) {
		this.address = address;
	}

    
}
