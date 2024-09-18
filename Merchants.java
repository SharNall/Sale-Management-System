package com.example.sale_management;

public class Merchants {
    private int MerchantsID;//primary key from merchants table
    private String name;
    private float total_debts;
    private String phone;
    private String address;

    public Merchants(String name, float total_debts, String phone, String address) {
        this.name = name;
        this.total_debts = total_debts;
        this.phone = phone;
        this.address = address;
    }
    
	public Merchants(int merchantsID, String name, float total_debts, String phone, String address) {
		super();
		MerchantsID = merchantsID;
		this.name = name;
		this.total_debts = total_debts;
		this.phone = phone;
		this.address = address;
	}

	public int getMerchantsID() {
		return MerchantsID;
	}

	public String getName() {
		return name;
	}

	public float getTotal_debts() {
		return total_debts;
	}

	public String getPhone() {
		return phone;
	}

	public String getAddress() {
		return address;
	}

	public void setMerchantsID(int merchantsID) {
		MerchantsID = merchantsID;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTotal_debts(float total_debts) {
		this.total_debts = total_debts;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setAddress(String address) {
		this.address = address;
	}

    
}